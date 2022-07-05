package com.example.gson

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import org.objectweb.asm.commons.AdviceAdapter

/**
 * User: linxiaodong
 * Date: 2022/7/1
 * Time: 4:24 PM
 */
class GsonClassVisitor(cv: ClassVisitor?) : ClassVisitor(Opcodes.ASM5, cv) {

    override fun visitMethod(
        access: Int,
        name: String?,
        desc: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        println("com.example.gson.GsonClassVisitor visitMethod name:$name")
        val methodVisitor = super.visitMethod(access, name, desc, signature, exceptions)
        if (!name.equals("fromJson")) {
            return methodVisitor
        }
        //参数个数
        val args = Type.getArgumentTypes(desc)
        if (args.size != 2
            || args.get(0) != Type.getType(String::class.java)
            || args.get(1) != Type.getType(java.lang.reflect.Type::class.java)
        ) {
            return methodVisitor
        }

        //访问fromJson方法
        val newMethodVisitor = FromJsonMethodVisitor(
            methodVisitor, access, name, desc
        )
        return newMethodVisitor
    }
}


class FromJsonMethodVisitor(
    val methodVisitor: MethodVisitor,
    access: Int,
    name: String?,
    descriptor: String?
) : AdviceAdapter(Opcodes.ASM5, methodVisitor, access, name, descriptor) {


    val arg0 = Type.getArgumentTypes(descriptor).get(0)
    val arg1 = Type.getArgumentTypes(descriptor).get(1)

    override fun onMethodEnter() {
        println("onMethodEnter arg0:${arg0.internalName},arg1:${arg1.internalName}")

        methodVisitor.visitFieldInsn(
            GETSTATIC,
            "com/example/gsonparserplugin/GsonRecorder",
            "INSTANCE",
            "Lcom/example/gsonparserplugin/GsonRecorder;"
        );
        //从局部变量1中装载引用类型值入栈
        mv.visitVarInsn(ALOAD, 1)
        mv.visitVarInsn(ALOAD, 2)
        methodVisitor.visitMethodInsn(
            INVOKEVIRTUAL,
            "com/example/gsonparserplugin/GsonRecorder",
            "methodEnter",
            "(Ljava/lang/String;Ljava/lang/reflect/Type;)V",
            false
        );

        super.onMethodEnter()
    }

    override fun onMethodExit(opcode: Int) {

        super.onMethodExit(opcode)
    }
}
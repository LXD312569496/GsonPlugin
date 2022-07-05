package com.example.gson

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import com.android.build.api.instrumentation.InstrumentationParameters
import org.objectweb.asm.ClassVisitor

/**
 * User: linxiaodong
 * Date: 2022/7/1
 * Time: 4:22 PM
 */
abstract class GsonParserTransform: AsmClassVisitorFactory<InstrumentationParameters.None>{

    /**
     * 自定义ClassVisitor
     */
    override fun createClassVisitor(
        classContext: ClassContext,
        nextClassVisitor: ClassVisitor
    ): ClassVisitor {
        println("createClassVisitor")
        return GsonClassVisitor(nextClassVisitor)
    }

    //是否需要处理这个类
    override fun isInstrumentable(classData: ClassData): Boolean {
        return classData.className.equals("com.google.gson.Gson")
    }
}
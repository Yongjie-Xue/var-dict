package com.xue.vardict

import com.intellij.lang.documentation.AbstractDocumentationProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiNamedElement
import java.io.File
import java.nio.charset.StandardCharsets

class DictDocumentationProvider : AbstractDocumentationProvider() {

    // 默认文件名改为 dictionary.txt，放在用户根目录
    private val dictPath = System.getProperty(
        "vardict.path",
        "${System.getProperty("user.home")}/dictionary.txt"
    )
    private val dictFile = File(dictPath)
    private var dict: Map<String, String> = emptyMap()

    init {
        reloadDict()
        println("[VarDictHover] 词典加载完成，共 ${dict.size} 条，文件路径: ${dictFile.absolutePath}")
    }

    private fun reloadDict() {
        if (!dictFile.exists()) {
            println("[VarDictHover] 词典文件不存在: ${dictFile.absolutePath}")
            dict = emptyMap()
            return
        }
        val map = mutableMapOf<String, String>()
        dictFile.readLines(StandardCharsets.UTF_8).forEach { line ->
            val trimLine = line.trim()
            if (trimLine.isEmpty() || trimLine.startsWith("#")) return@forEach
            val parts = trimLine.split(Regex("\\s+"), limit = 2)
            if (parts.size == 2) {
                map[parts[0]] = parts[1]
            }
        }
        dict = map
    }

    override fun generateDoc(element: PsiElement, originalElement: PsiElement?): String? {
        val name = when {
            element is PsiNamedElement -> element.name
            originalElement is PsiIdentifier -> originalElement.text
            else -> null
        } ?: return null

        val desc = dict[name]
        println("[VarDictHover] 触发变量: $name, 匹配结果: $desc")

        if (desc == null) return null
        return """
            <div style="color:#0969da; margin-top:6px;">
                <b>自定义释义：</b>$desc
            </div>
        """.trimIndent()
    }
}

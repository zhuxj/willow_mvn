/**
 * 版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2015 
 * 日    期：15-8-28
 */
package com.willow.codegen.plugin;

import com.willow.codegen.CodegenManager;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.00
 * @goal codeGen
 */
public class codeGenMojo extends AbstractMojo {
    /**
     * @parameter alias="projectBaseDir"
     */
    private String baseDir;

    /**
     * @parameter alias="codeGenConfigPath"
     */
    private String codeGenConfigPath;

    public void execute()
            throws MojoExecutionException {
        getLog().info("codeGen starting");
        CodegenManager codegenManager = new CodegenManager();
        String codegenConfigFullPath = baseDir + codeGenConfigPath;
        try {
            codegenManager.generateFromAnt(baseDir, codegenConfigFullPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getLog().info("codeGen end");
    }
}

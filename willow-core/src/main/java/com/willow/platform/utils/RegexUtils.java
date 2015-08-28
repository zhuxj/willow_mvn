package com.willow.platform.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.oro.text.regex.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 基于ORO的正则表达式工具类.
 * @author Claude
 *
 */
public abstract class RegexUtils {
	
	/**
	 * 从文本中抓取字符串并转化为对象集合.
	 * <pre>
	 * 	
	 *  String src = "&lt;h1&gt;用户列表&lt;/h1&gt;&lt;p&gt;用户名:Claude &lt;br/&gt; 年龄:29&lt;/p&gt;&lt;p&gt;用户名:John &lt;br/&gt; 年龄:28&lt;/p&gt;";
	 *  List users = RegexUtils.getObjects(src, "&lt;p&gt;用户名:(.+?) &lt;br/&gt; 年龄:(\\d+)&lt;/p&gt;", 
	 *  new MatcherCollector(){
	 *  	public UserInfo match(MatchResult matchResult) {
	 *  		UserInfo user = new UserInfo();
	 *  		user.setName(matchResult.group(1));
	 *  		user.setAge(Integer.parseInt(matchResult.group(2)));
	 *  		return user;
	 *  	}		
	 *  });
	 * </pre> 
	 * 
	 * @param src 源字符串.
	 * @param regex Perl5正则表达式.
	 * @param collector 匹配结果对象收集器.
	 * @return 返回结果对象数组.
	 */
	public static List getObjects(String src, String regex, MatcherCollector collector){
		if(StringUtils.isBlank(src)){
			throw new IllegalArgumentException("源字符串不能为空!");
		}
		
		if(StringUtils.isBlank(regex)){
			throw new IllegalArgumentException("正则表达式不能为空!");
		}
		
		List resultList = new ArrayList();
		Pattern regexPattern = null;
		try {
			regexPattern = new Perl5Compiler().compile(regex, Perl5Compiler.SINGLELINE_MASK);
		} catch (MalformedPatternException e) {
			e.printStackTrace();
		}
		
		if(regexPattern != null){
			Perl5Matcher matcher = new Perl5Matcher();
			PatternMatcherInput matcherInput = new PatternMatcherInput(src);
			while(matcher.contains(matcherInput, regexPattern)){
				MatchResult result = matcher.getMatch();
				Object obj = collector.match(result);
                if(obj != null){
				    resultList.add(obj);
                }
			}
		}
		return resultList;
	}
	
	/**
	 * 通过正则表达式抓取字符串中间的指定子串集合.
	 * <pre>
	 *     String src = "&lt;a href='a.html'&gt;Hello&lt;/a&gt; world! Regxp.&lt;a href='b.html'&gt;Hello&lt;/a&gt; ";
	 *     List&lt;String&gt; result = RegexUtils.getStrings(src, "&lt;a href='(.+?)'&gt;", 1);
	 *     //结果为 a.html, b.html两个子串.
	 * </pre>
	 * @param src 源字符串.
	 * @param regex Perl5正则表达式.
	 * @param group 匹配组号.
	 * @return 匹配的子串集合.
	 * @throws org.apache.oro.text.regex.MalformedPatternException
	 */
	public static List<String> getStrings(String src, String regex, int group) {
		if(StringUtils.isBlank(src)){
			throw new IllegalArgumentException("源字符串不能为空!");
		}

		if(StringUtils.isBlank(regex)){
			throw new IllegalArgumentException("正则表达式不能为空!");
		}

		List<String> result = new ArrayList<String>();

        Pattern produceRegexPattern = null;
        try {
            produceRegexPattern = new Perl5Compiler().compile(regex, Perl5Compiler.SINGLELINE_MASK);
        } catch (MalformedPatternException e) {
            e.printStackTrace();
            return result;
        }

        Perl5Matcher matcher = new Perl5Matcher();
		PatternMatcherInput matcherInput = new PatternMatcherInput(src);
		while(matcher.contains(matcherInput, produceRegexPattern)){
			MatchResult matchResult = matcher.getMatch();
			result.add(matchResult.group(group));
		}

		return result;
	}

    /**
     * 通过正则表达式抓取字符串中间的指定子串集合.
     * <pre>
     *     String src = "&lt;a href='a.html'&gt;Hello&lt;/a&gt; world! Regxp.&lt;a href='b.html'&gt;Hello&lt;/a&gt; ";
     *     List&lt;String&gt; result = RegexUtils.getStrings(src, "&lt;a href='(.+?)'&gt;", 1);
     *     //结果为 a.html, b.html两个子串.
     * </pre>
     * @param src 源字符串.
     * @param regex Perl5正则表达式.
     * @param group 匹配组号.
     * @return 匹配的子串集合.
     * @throws org.apache.oro.text.regex.MalformedPatternException
     */
    public static Set<String> getStrings2(String src, String regex, int group) {
        if(StringUtils.isBlank(src)){
            throw new IllegalArgumentException("源字符串不能为空!");
        }

        if(StringUtils.isBlank(regex)){
            throw new IllegalArgumentException("正则表达式不能为空!");
        }

        Set<String> result = new HashSet<String>();

        Pattern produceRegexPattern = null;
        try {
            produceRegexPattern = new Perl5Compiler().compile(regex, Perl5Compiler.SINGLELINE_MASK);
        } catch (MalformedPatternException e) {
            e.printStackTrace();
            return result;
        }

        Perl5Matcher matcher = new Perl5Matcher();
        PatternMatcherInput matcherInput = new PatternMatcherInput(src);
        while(matcher.contains(matcherInput, produceRegexPattern)){
            MatchResult matchResult = matcher.getMatch();
            result.add(matchResult.group(group));
        }

        return result;
    }

    public static String getString(String src, String regex) {
        if(StringUtils.isBlank(src)){
            throw new IllegalArgumentException("源字符串不能为空!");
        }

        if(StringUtils.isBlank(regex)){
            throw new IllegalArgumentException("正则表达式不能为空!");
        }

        String result = src;

        Pattern produceRegexPattern;
        try {
            produceRegexPattern = new Perl5Compiler().compile(regex, Perl5Compiler.SINGLELINE_MASK);
        } catch (MalformedPatternException e) {
            e.printStackTrace();
            return result;
        }

        Perl5Matcher matcher = new Perl5Matcher();
        PatternMatcherInput matcherInput = new PatternMatcherInput(src);
        while(matcher.contains(matcherInput, produceRegexPattern)){
            MatchResult matchResult = matcher.getMatch();
            result = matchResult.toString();
        }

        return result;
    }


	/**
	 * 通过正则表达式抓取字符串中间的指定子串.
	 * <pre>
	 *     String src = "&lt;a href='a.html'&gt;Hello&lt;/a&gt; world! Regxp.";
	 *     String result = RegexUtils.getString(src, "&lt;a href='(.+?)'&gt;", 1);
	 *     //result的值为"a.html"
	 * </pre>
	 * @param src 源字符串.
	 * @param regex Perl5正则表达式.
	 * @param group 匹配组号.
	 * @return 匹配的子串.
	 * @throws org.apache.oro.text.regex.MalformedPatternException
	 */
	public static final String getString(String src, String regex, int group){
		if(StringUtils.isBlank(src)){
			throw new IllegalArgumentException("源字符串不能为空!");
		}

		if(StringUtils.isBlank(regex)){
			throw new IllegalArgumentException("正则表达式不能为空!");
		}

        Pattern produceRegexPattern = null;
        try {
            produceRegexPattern = new Perl5Compiler().compile(regex, Perl5Compiler.SINGLELINE_MASK);
        } catch (MalformedPatternException e) {
            e.printStackTrace();
            return null;
        }

        Perl5Matcher matcher = new Perl5Matcher();
		PatternMatcherInput matcherInput = new PatternMatcherInput(src);
		if(matcher.contains(matcherInput, produceRegexPattern)){
			MatchResult result = matcher.getMatch();
			return result.group(group);
		}

		return "";
	}

    /**
     * 通过正则表达式抓取字符串中间的指定子串.
     * <pre>
     *     String src = "&lt;a href='a.html'&gt;Hello&lt;/a&gt; world! Regxp.";
     *     String result = RegexUtils.getString(src, "&lt;a href='(.+?)'&gt;", 1);
     *     //result的值为"a.html"
     * </pre>
     * @param src 源字符串.
     * @param regex Perl5正则表达式.
     * @param group 匹配组号.
     * @return 匹配的子串.
     * @throws org.apache.oro.text.regex.MalformedPatternException
     */
    public static final String getString2(String src, String regex, int group){
        if(StringUtils.isBlank(src)){
            throw new IllegalArgumentException("源字符串不能为空!");
        }

        if(StringUtils.isBlank(regex)){
            throw new IllegalArgumentException("正则表达式不能为空!");
        }

        Pattern produceRegexPattern = null;
        try {
            produceRegexPattern = new Perl5Compiler().compile(regex, Perl5Compiler.CASE_INSENSITIVE_MASK);
        } catch (MalformedPatternException e) {
            e.printStackTrace();
            return null;
        }

        Perl5Matcher matcher = new Perl5Matcher();
        PatternMatcherInput matcherInput = new PatternMatcherInput(src);
        if(matcher.contains(matcherInput, produceRegexPattern)){
            MatchResult result = matcher.getMatch();
            return result.group(group);
        }

        return "";
    }

	public static final Object match(String src, String regex, MatcherCollector collector) {
		if(StringUtils.isBlank(src)){
			throw new IllegalArgumentException("源字符串不能为空!");
		}

		if(StringUtils.isBlank(regex)){
			throw new IllegalArgumentException("正则表达式不能为空!");
		}

        Pattern produceRegexPattern = null;
        try {
            produceRegexPattern = new Perl5Compiler().compile(regex, Perl5Compiler.SINGLELINE_MASK);
        } catch (MalformedPatternException e) {
            e.printStackTrace();
        }

        Perl5Matcher matcher = new Perl5Matcher();
		PatternMatcherInput matcherInput = new PatternMatcherInput(src);
		if(matcher.contains(matcherInput, produceRegexPattern)){
			MatchResult result = matcher.getMatch();
			return collector.match(result);
		}

		return null;
	}
}

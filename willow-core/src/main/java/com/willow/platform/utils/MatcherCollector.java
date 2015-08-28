package com.willow.platform.utils;

import org.apache.oro.text.regex.MatchResult;

/**
 * 匹配结果收集器.
 * @author 姜文樟
 * @version 1.0
 *          功能说明：
 */
public interface MatcherCollector {

	/**
	 * 通过MatchResult构造对象.
	 * @param matchResult
	 * @return 结果对象.
	 */
	public Object match(MatchResult matchResult);

}

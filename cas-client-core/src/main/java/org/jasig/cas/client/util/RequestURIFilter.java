package org.jasig.cas.client.util;

import static org.jasig.cas.client.util.BasicConstant.*;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.jasig.cas.client.util.regex.PathNameWildcardCompiler;

/**
 * 用来匹配和过滤指定路径。
 *
 * @author Cruise Xu
 */
public class RequestURIFilter {
	
    public static final String EXCLUDE_PREFIX = "!";
    private final String[]  uris;
    private final boolean[] excludes;
    private final Pattern[] patterns;

    public RequestURIFilter(String uris) {
        List<String> names = new LinkedList<String>();
        List<Boolean> excludes = new LinkedList<Boolean>();
        List<Pattern> patterns = new LinkedList<Pattern>();
        
        if(null == uris)
        	uris = EMPTY_STRING;
        for (String uri : StringUtil.split(uris, ", \r\n")) {
            uri = StringUtil.trimToNull(uri);

            if (uri != null) {
                String fullUri = uri;
                boolean exclude = uri.startsWith(EXCLUDE_PREFIX);

                if (exclude) {
                    uri = StringUtil.trimToNull(uri.substring(EXCLUDE_PREFIX.length()));
                }

                if (uri != null) {
                    names.add(fullUri);
                    excludes.add(exclude);
                    patterns.add(PathNameWildcardCompiler.compilePathName(uri));
                }
            }
        }

        if (!patterns.isEmpty()) {
            this.uris = names.toArray(new String[names.size()]);
            this.patterns = patterns.toArray(new Pattern[patterns.size()]);
            this.excludes = new boolean[excludes.size()];

            for (int i = 0; i < excludes.size(); i++) {
                this.excludes[i] = excludes.get(i);
            }
        } else {
            this.uris = EMPTY_STRING_ARRAY;
            this.excludes = EMPTY_BOOLEAN_ARRAY;
            this.patterns = null;
        }
    }

    public boolean matches(String path) {
        if (patterns != null) {
            for (int i = patterns.length - 1; i >= 0; i--) {
                if (patterns[i].matcher(path).find()) {
                    return !excludes[i];
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("FilterOf").append(uris).toString();
    }
}

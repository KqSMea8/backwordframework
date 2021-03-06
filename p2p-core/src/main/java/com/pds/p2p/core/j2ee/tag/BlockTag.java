package com.pds.p2p.core.j2ee.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class BlockTag extends TagSupport {
    /**
     *
     */
    private static final long serialVersionUID = -1025483527658303820L;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int doStartTag() throws JspException {
        return getOverriedContent() == null ? EVAL_BODY_INCLUDE : SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        String overriedContent = getOverriedContent();
        if (overriedContent == null) {
            return EVAL_PAGE;
        }

        try {
            pageContext.getOut().write(overriedContent);
        } catch (IOException e) {
            throw new JspException("tag output error", e);
        }
        return EVAL_PAGE;
    }

    private String getOverriedContent() {
        String varName = Utils.getOverrideVariableName(name);
        return (String) pageContext.getAttribute(varName);
    }
}

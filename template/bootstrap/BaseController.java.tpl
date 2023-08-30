package [[= map.get("package.prefix.dot")]]controllers;

import java.util.Map;
import com.pmdesigns.jvc.JVCRequestContext;
import com.pmdesigns.jvc.tools.HtmlEncoder;

public class BaseController {
    public final JVCRequestContext rc;
    public Map<String,String> errors;


    public BaseController(JVCRequestContext rc) {
        this.rc = rc;
    }


	/**
	 * Form helper for text field inputs
	 */
	public String textInput(String name, String value, String html) {
		StringBuilder sb = new StringBuilder();
		sb.append("<input type='text' name='").append(name).append("'");
		if (value != null) sb.append(" value='").append(HtmlEncoder.encode(value)).append("'");
		if (html != null) sb.append(" ").append(html);
		sb.append(">");
		if (errors != null && errors.get(name) != null) {
			sb.append("<span class='input_error'>").append(HtmlEncoder.encode(errors.get(name))).append("</span>");
		}
		return sb.toString();
	}
	public String textInput(String name, Object value, String html) {
		return textInput(name, (value == null ? "" : value.toString()), html);
	}
	public String textInput(String name, int value, String html) {
		return textInput(name, Integer.toString(value), html);
	}
	public String textInput(String name, short value, String html) {
		return textInput(name, Short.toString(value), html);
	}
	public String textInput(String name, long value, String html) {
		return textInput(name, Long.toString(value), html);
	}
	public String textInput(String name, float value, String html) {
		return textInput(name, Float.toString(value), html);
	}
	public String textInput(String name, double value, String html) {
		return textInput(name, Double.toString(value), html);
	}
	public String textInput(String name, char value, String html) {
		return textInput(name, Character.toString(value), html);
	}
	public String textInput(String name, byte value, String html) {
		return textInput(name, Byte.toString(value), html);
	}
	public String textInput(String name, boolean value, String html) {
		return textInput(name, Boolean.toString(value), html);
	}

	/**
	 * Form helper for text field inputs that are part of a model object
	 */
	public String textInput(String modelVariable, String field, String value, String html) {
		StringBuilder sb = new StringBuilder();
		sb.append("<input type='text' name='").append(modelVariable).append("[").append(field).append("]'");
		if (value != null) sb.append(" value='").append(HtmlEncoder.encode(value)).append("'");
		if (html != null) sb.append(" ").append(html);
		sb.append(">");
		if (errors != null && errors.get(field) != null) {
			sb.append("<span class='input_error'>").append(HtmlEncoder.encode(errors.get(field))).append("</span>");
		}
		return sb.toString();
	}
	public String textInput(String modelVariable, String field, Object value, String html) {
		return textInput(modelVariable, field, (value == null ? "" : value.toString()), html);
	}
	public String textInput(String modelVariable, String field, int value, String html) {
		return textInput(modelVariable, field, Integer.toString(value), html);
	}
	public String textInput(String modelVariable, String field, short value, String html) {
		return textInput(modelVariable, field, Short.toString(value), html);
	}
	public String textInput(String modelVariable, String field, long value, String html) {
		return textInput(modelVariable, field, Long.toString(value), html);
	}
	public String textInput(String modelVariable, String field, float value, String html) {
		return textInput(modelVariable, field, Float.toString(value), html);
	}
	public String textInput(String modelVariable, String field, double value, String html) {
		return textInput(modelVariable, field, Double.toString(value), html);
	}
	public String textInput(String modelVariable, String field, char value, String html) {
		return textInput(modelVariable, field, Character.toString(value), html);
	}
	public String textInput(String modelVariable, String field, byte value, String html) {
		return textInput(modelVariable, field, Byte.toString(value), html);
	}
	public String textInput(String modelVariable, String field, boolean value, String html) {
		return textInput(modelVariable, field, Boolean.toString(value), html);
	}

	/**
	 * Form helper for select inputs with string values
	 */
	public String selectInput(String name, String value, String[] names, String[] values) {
		StringBuilder sb = new StringBuilder();
		sb.append("<select name='").append(name).append("'>\n");
		int i=0;
		for (String val : values) {
			sb.append("<option value='").append(val).append("'");
			if (val.equals(value)) sb.append(" selected=true");
			sb.append(">").append(names[i++]).append("</option>\n");
		}
		sb.append("</select>\n");
		return sb.toString();
	}

	/**
	 * Form helper for select inputs with integer values
	 */
	public String selectInput(String name, int value, String[] names, int[] values) {
		StringBuilder sb = new StringBuilder();
		sb.append("<select name='").append(name).append("'>\n");
		int i=0;
		for (int val : values) {
			sb.append("<option value='").append(val).append("'");
			if (val == value) sb.append(" selected=true");
			sb.append(">").append(names[i++]).append("</option>\n");
		}
		sb.append("</select>\n");
		return sb.toString();
	}
}


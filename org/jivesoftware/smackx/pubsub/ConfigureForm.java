package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.FormField.Type;
import org.jivesoftware.smackx.xdata.packet.DataForm;

public class ConfigureForm extends Form {
    public ConfigureForm(Form form) {
        super(form.getDataFormToSend());
    }

    public ConfigureForm(DataForm dataForm) {
        super(dataForm);
    }

    private String m5981a(ConfigureNodeFields configureNodeFields) {
        FormField field = getField(configureNodeFields.getFieldName());
        return field.getValues().isEmpty() ? null : (String) field.getValues().get(0);
    }

    private void m5982a(ConfigureNodeFields configureNodeFields, Type type) {
        String fieldName = configureNodeFields.getFieldName();
        if (getField(fieldName) == null) {
            FormField formField = new FormField(fieldName);
            formField.setType(type);
            addField(formField);
        }
    }

    public String getTitle() {
        return m5981a(ConfigureNodeFields.title);
    }

    public void setTitle(String str) {
        m5982a(ConfigureNodeFields.title, Type.text_single);
        setAnswer(ConfigureNodeFields.title.getFieldName(), str);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getClass().getName() + " Content [");
        for (FormField formField : getFields()) {
            stringBuilder.append('(');
            stringBuilder.append(formField.getVariable());
            stringBuilder.append(':');
            CharSequence stringBuilder2 = new StringBuilder();
            for (String str : formField.getValues()) {
                if (stringBuilder2.length() > 0) {
                    stringBuilder.append(',');
                }
                stringBuilder2.append(str);
            }
            if (stringBuilder2.length() == 0) {
                stringBuilder2.append("NOT SET");
            }
            stringBuilder.append(stringBuilder2);
            stringBuilder.append(')');
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}

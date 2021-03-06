package org.jivesoftware.smack.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.jivesoftware.smack.util.C0169k;
import org.jivesoftware.smack.util.XmlStringBuilder;

public final class Message extends Stanza implements C0169k<Message> {
    public static final String BODY = "body";
    public static final String ELEMENT = "message";
    private final Set<Body> bodies;
    private final Set<Subject> subjects;
    private String thread;
    private Type type;

    public static class Body {
        private final String language;
        private final String message;

        private Body(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("Language cannot be null.");
            } else if (str2 == null) {
                throw new NullPointerException("Message cannot be null.");
            } else {
                this.language = str;
                this.message = str2;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Body body = (Body) obj;
            return this.language.equals(body.language) && this.message.equals(body.message);
        }

        public String getLanguage() {
            return this.language;
        }

        public String getMessage() {
            return this.message;
        }

        public int hashCode() {
            return ((this.language.hashCode() + 31) * 31) + this.message.hashCode();
        }
    }

    public static class Subject {
        private final String language;
        private final String subject;

        private Subject(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("Language cannot be null.");
            } else if (str2 == null) {
                throw new NullPointerException("Subject cannot be null.");
            } else {
                this.language = str;
                this.subject = str2;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Subject subject = (Subject) obj;
            return this.language.equals(subject.language) && this.subject.equals(subject.subject);
        }

        public String getLanguage() {
            return this.language;
        }

        public String getSubject() {
            return this.subject;
        }

        public int hashCode() {
            return ((this.language.hashCode() + 31) * 31) + this.subject.hashCode();
        }
    }

    public enum Type {
        normal,
        chat,
        groupchat,
        headline,
        error;

        public static Type fromString(String str) {
            return valueOf(str.toLowerCase(Locale.US));
        }
    }

    public Message() {
        this.thread = null;
        this.subjects = new HashSet();
        this.bodies = new HashSet();
    }

    public Message(String str) {
        this.thread = null;
        this.subjects = new HashSet();
        this.bodies = new HashSet();
        setTo(str);
    }

    public Message(String str, String str2) {
        this(str);
        setBody(str2);
    }

    public Message(String str, Type type) {
        this(str);
        setType(type);
    }

    public Message(Message message) {
        super((Stanza) message);
        this.thread = null;
        this.subjects = new HashSet();
        this.bodies = new HashSet();
        this.type = message.type;
        this.thread = message.thread;
        this.subjects.addAll(message.subjects);
        this.bodies.addAll(message.bodies);
    }

    private String determineLanguage(String str) {
        String str2 = "".equals(str) ? null : str;
        return (str2 != null || this.language == null) ? str2 == null ? Stanza.getDefaultLanguage() : str2 : this.language;
    }

    private Body getMessageBody(String str) {
        String determineLanguage = determineLanguage(str);
        for (Body body : this.bodies) {
            if (determineLanguage.equals(body.language)) {
                return body;
            }
        }
        return null;
    }

    private Subject getMessageSubject(String str) {
        String determineLanguage = determineLanguage(str);
        for (Subject subject : this.subjects) {
            if (determineLanguage.equals(subject.language)) {
                return subject;
            }
        }
        return null;
    }

    public Body addBody(String str, String str2) {
        Body body = new Body(str2, null);
        this.bodies.add(body);
        return body;
    }

    public Subject addSubject(String str, String str2) {
        Subject subject = new Subject(str2, null);
        this.subjects.add(subject);
        return subject;
    }

    public Message clone() {
        return new Message(this);
    }

    public Set<Body> getBodies() {
        return Collections.unmodifiableSet(this.bodies);
    }

    public String getBody() {
        return getBody(null);
    }

    public String getBody(String str) {
        Body messageBody = getMessageBody(str);
        return messageBody == null ? null : messageBody.message;
    }

    public List<String> getBodyLanguages() {
        Body messageBody = getMessageBody(null);
        List arrayList = new ArrayList();
        for (Body body : this.bodies) {
            if (!body.equals(messageBody)) {
                arrayList.add(body.language);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public String getSubject() {
        return getSubject(null);
    }

    public String getSubject(String str) {
        Subject messageSubject = getMessageSubject(str);
        return messageSubject == null ? null : messageSubject.subject;
    }

    public List<String> getSubjectLanguages() {
        Subject messageSubject = getMessageSubject(null);
        List arrayList = new ArrayList();
        for (Subject subject : this.subjects) {
            if (!subject.equals(messageSubject)) {
                arrayList.add(subject.language);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Set<Subject> getSubjects() {
        return Collections.unmodifiableSet(this.subjects);
    }

    public String getThread() {
        return this.thread;
    }

    public Type getType() {
        return this.type == null ? Type.normal : this.type;
    }

    public boolean removeBody(String str) {
        String determineLanguage = determineLanguage(str);
        for (Body body : this.bodies) {
            if (determineLanguage.equals(body.language)) {
                return this.bodies.remove(body);
            }
        }
        return false;
    }

    public boolean removeBody(Body body) {
        return this.bodies.remove(body);
    }

    public boolean removeSubject(String str) {
        String determineLanguage = determineLanguage(str);
        for (Subject subject : this.subjects) {
            if (determineLanguage.equals(subject.language)) {
                return this.subjects.remove(subject);
            }
        }
        return false;
    }

    public boolean removeSubject(Subject subject) {
        return this.subjects.remove(subject);
    }

    public void setBody(String str) {
        if (str == null) {
            removeBody("");
        } else {
            addBody(null, str);
        }
    }

    public void setSubject(String str) {
        if (str == null) {
            removeSubject("");
        } else {
            addSubject(null, str);
        }
    }

    public void setThread(String str) {
        this.thread = str;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
        xmlStringBuilder.halfOpenElement(ELEMENT);
        addCommonAttributes(xmlStringBuilder);
        xmlStringBuilder.optAttribute("type", this.type);
        xmlStringBuilder.rightAngleBracket();
        Subject messageSubject = getMessageSubject(null);
        if (messageSubject != null) {
            xmlStringBuilder.element("subject", messageSubject.subject);
        }
        for (Subject subject : getSubjects()) {
            if (!subject.equals(messageSubject)) {
                xmlStringBuilder.halfOpenElement("subject").xmllangAttribute(subject.language).rightAngleBracket();
                xmlStringBuilder.escape(subject.subject);
                xmlStringBuilder.closeElement("subject");
            }
        }
        Body messageBody = getMessageBody(null);
        if (messageBody != null) {
            xmlStringBuilder.element(BODY, messageBody.message);
        }
        for (Body body : getBodies()) {
            if (!body.equals(messageBody)) {
                xmlStringBuilder.halfOpenElement(BODY).xmllangAttribute(body.getLanguage()).rightAngleBracket();
                xmlStringBuilder.escape(body.getMessage());
                xmlStringBuilder.closeElement(BODY);
            }
        }
        xmlStringBuilder.optElement("thread", this.thread);
        if (this.type == Type.error) {
            appendErrorIfExists(xmlStringBuilder);
        }
        xmlStringBuilder.append(getExtensionsXML());
        xmlStringBuilder.closeElement(ELEMENT);
        return xmlStringBuilder;
    }
}

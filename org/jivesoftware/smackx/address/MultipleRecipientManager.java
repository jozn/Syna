package org.jivesoftware.smackx.address;

import org.jivesoftware.smack.packet.Stanza;

public class MultipleRecipientManager {

    private static class PacketCopy extends Stanza {
        private CharSequence text;

        public PacketCopy(CharSequence charSequence) {
            this.text = charSequence;
        }

        public CharSequence toXML() {
            return this.text;
        }
    }
}

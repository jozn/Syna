package org.jivesoftware.smack.provider;

import java.util.Collection;

/* renamed from: org.jivesoftware.smack.provider.b */
public interface ProviderLoader {
    Collection<ExtensionProviderInfo> getExtensionProviderInfo();

    Collection<IQProviderInfo> getIQProviderInfo();

    Collection<StreamFeatureProviderInfo> getStreamFeatureProviderInfo();
}

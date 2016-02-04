package mobi.mmdt.ott.p043b.p044a;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Random;
import mobi.mmdt.ott.p043b.p047b.RestfulWS;
import mobi.mmdt.p041a.hash;
import org.p074b.JSONArray;
import org.p074b.JSONException;
import org.p074b.JSONObject;

/* renamed from: mobi.mmdt.ott.b.a.a */
public class AmirWebservice {
    private static AmirWebservice f3389a;
    private Context f3390b;
    private String f3391c;
    private String f3392d;
    private String f3393e;

    private AmirWebservice(Context context, String str, String str2, String str3) {
        this.f3390b = context;
        this.f3391c = str;
        this.f3393e = str2;
        this.f3392d = str3;
    }

    public static AmirWebservice m4314a(Context context, String str, String str2, String str3) {
        if (f3389a == null) {
            f3389a = new AmirWebservice(context, str, str2, str3);
        }
        return f3389a;
    }

    public PublicRoomsResult m4315a() throws JSONException, IOException, NumberFormatException, NameNotFoundException, AmirWebserviceException, GeneralSecurityException {
        long currentTimeMillis = System.currentTimeMillis();
        String a = hash.m4111a(new StringBuilder(String.valueOf((new Random(System.currentTimeMillis()).nextLong() | currentTimeMillis) | Long.parseLong(this.f3393e))).toString());
        JSONObject jSONObject = new JSONObject();
        jSONObject.m5359a("ClientAuthData", new ClientAuthData(this.f3390b).m4324a(a, currentTimeMillis, this.f3393e, this.f3392d));
        JSONObject b = RestfulWS.m4339b(this.f3390b, this.f3391c + "/" + "publicchat" + "/" + "roomsList", jSONObject, null);
        if (new StringBuilder(String.valueOf(b.m5361c("ResultCode"))).toString().startsWith("2")) {
            JSONArray d = b.m5362d("Rooms");
            Room[] roomArr = new Room[d.m5344a()];
            for (int i = 0; i < roomArr.length; i++) {
                roomArr[i] = new Room(d.m5348b(i).m5365g("JID"), d.m5348b(i).m5365g("Nickname"), d.m5348b(i).m5365g("Description"), d.m5348b(i).m5365g("Subject"), d.m5348b(i).m5365g("ThumbnailURL"), d.m5348b(i).m5365g("CreationDate"), d.m5348b(i).m5365g("FollowersCount"), d.m5348b(i).m5365g("Mode"), d.m5348b(i).m5361c("Followed"));
            }
            return new PublicRoomsResult(b.m5361c("ResultCode"), b.m5361c("RoomsCount"), roomArr);
        }
        throw new AmirWebserviceException(b.m5361c("ResultCode"), b.m5365g("ResultMessage"));
    }

    public SendGiftResult m4316a(String str, String str2) throws JSONException, IOException, AmirWebserviceException, NumberFormatException, NameNotFoundException, GeneralSecurityException {
        long currentTimeMillis = System.currentTimeMillis();
        String a = hash.m4111a(new StringBuilder(String.valueOf((new Random(System.currentTimeMillis()).nextLong() | currentTimeMillis) | Long.parseLong(this.f3393e))).toString());
        JSONObject jSONObject = new JSONObject();
        jSONObject.m5359a("ClientAuthData", new ClientAuthData(this.f3390b).m4324a(a, currentTimeMillis, this.f3393e, this.f3392d));
        jSONObject.m5359a("Language", (Object) str);
        jSONObject.m5359a("MessageType", (Object) "req_contactgift");
        jSONObject.m5359a("Contact", (Object) str2);
        JSONObject b = RestfulWS.m4339b(this.f3390b, this.f3391c + "/" + "contactgift", jSONObject, null);
        if (new StringBuilder(String.valueOf(b.m5361c("ResultCode"))).toString().startsWith("2")) {
            return new SendGiftResult(b.m5365g("MessageType"), b.m5361c("ResultCode"), b.m5365g("GiftAmount"));
        }
        throw new AmirWebserviceException(b.m5361c("ResultCode"), b.m5365g("ResultMessage"));
    }

    public SetFollowPublicRoomResult m4317a(String str, boolean z) throws NumberFormatException, JSONException, IOException, AmirWebserviceException, NameNotFoundException, GeneralSecurityException {
        long currentTimeMillis = System.currentTimeMillis();
        String a = hash.m4111a(new StringBuilder(String.valueOf((new Random(System.currentTimeMillis()).nextLong() | currentTimeMillis) | Long.parseLong(this.f3393e))).toString());
        JSONObject jSONObject = new JSONObject();
        jSONObject.m5359a("ClientAuthData", new ClientAuthData(this.f3390b).m4324a(a, currentTimeMillis, this.f3393e, this.f3392d));
        jSONObject.m5359a("JID", (Object) str);
        if (z) {
            jSONObject.m5359a("Action", (Object) "follow");
        } else {
            jSONObject.m5359a("Action", (Object) "unfollow");
        }
        JSONObject b = RestfulWS.m4339b(this.f3390b, this.f3391c + "/" + "publicchat" + "/" + "follow", jSONObject, null);
        if (new StringBuilder(String.valueOf(b.m5361c("ResultCode"))).toString().startsWith("2")) {
            return new SetFollowPublicRoomResult(b.m5361c("ResultCode"));
        }
        throw new AmirWebserviceException(b.m5361c("ResultCode"), b.m5365g("ResultMessage"));
    }
}

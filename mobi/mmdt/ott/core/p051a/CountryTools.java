package mobi.mmdt.ott.core.p051a;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import mobi.mmdt.ott.core.R.R;
import mobi.mmdt.ott.core.model.p058a.AppSettings;
import mobi.mmdt.ott.core.p051a.p052a.NumberPhoneFormatExeption;
import org.jivesoftware.smackx.xhtmlim.XHTMLText;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* renamed from: mobi.mmdt.ott.core.a.c */
public class CountryTools {
    private static CountryTools f3494a;
    private static final String[] f3495g;
    private static final String[] f3496h;
    private List<String> f3497b;
    private List<String> f3498c;
    private HashMap<String, String> f3499d;
    private HashMap<String, String> f3500e;
    private HashMap<String, Integer> f3501f;

    static {
        f3495g = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Ascension Island", "Australia", "Australian Antarctic Territory", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "British Indian Ocean Territory", "Brunei", "Bulgaria", "Burkina Faso", "Burma", "Burundi", "Cambodia", "Cameroon", "Cape Verde", "Caribbean Netherlands", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Christmas Island", "Colombia", "Comoros", "Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands", "Faroe Islands", "Federated States of Micronesia", "Fiji", "Finland", "France", "French Guiana", "French Polynesia", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mexico", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco", "Mozambique", "Namibia", "Nauru", "Nepal", "Netherlands", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "North Korea", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Palestinian territories", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Puerto Rico", "Puerto Rico", "Qatar", "Romania", "Russia", "Rwanda", "Reunion", "Saint Helena", "Saint Kitts & Nevis Anguilla", "Saint Tome ", "Saint Vincent & Grenadines", "Saint-Pierre and Miquelon", "Samoa", "San Marino", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "British Virgin Islands", "U.S. Virgin Islands", "Wallis and Futuna", "Yemen", "Zambia", "Zimbabwe"};
        f3496h = new String[]{"0093", "00355", "00213", "001684", "00376", "00244", "001268", "0054", "00374", "00297", "00247", "0061", "006721", "0043", "00994", "001242", "00973", "00880", "001246", "00375", "0032", "00501", "00229", "001441", "00975", "00591", "00387", "00267", "0055", "00246", "00673", "00359", "00226", "0095", "00257", "00855", "00237", "00238", "00599", "001345", "00236", "00235", "0056", "0086", "00618", "00618", "0057", "00269", "00682", "00506", "00385", "0053", "00357", "00420", "00243", "0045", "00253", "001767", "001809", "001829", "00670", "00593", "0020", "00503", "00240", "00291", "00372", "00251", "00500", "00298", "00691", "00679", "00358", "0033", "00594", "00689", "00241", "00220", "00995", "0049", "00233", "00350", "0030", "00299", "001473", "00590", "001671", "00502", "00224", "00245", "00592", "00509", "00504", "00852", "0036", "00354", "0091", "0062", "0098", "00964", "00353", "0039", "00225", "001876", "0081", "00962", "0077", "00254", "00686", "00965", "00996", "00856", "00371", "00961", "001758", "00266", "00231", "00218", "00423", "00370", "00352", "00853", "00389", "00261", "00265", "0060", "00960", "00223", "00356", "00692", "00596", "00222", "00230", "0052", "00373", "00377", "00976", "00382", "001664", "00212", "00258", "00264", "00674", "00977", "0031", "00687", "0064", "00505", "00227", "00234", "00683", "006723", "00850", "001670", "0047", "00968", "0092", "00680", "00970", "00507", "00675", "00595", "0051", "0063", "0048", "00351", "001787", "001939", "00974", "0040", "007", "00250", "00262", "00290", "001869", "00239", "001784", "00508", "00685", "00378", "00966", "00221", "00381", "00248", "00232", "0065", "00421", "00386", "00677", "00252", "0027", "0082", "0034", "0094", "00249", "00597", "00268", "0046", "0041", "00963", "00886", "00992", "00255", "0066", "00228", "00690", "00676", "001868", "00216", "0090", "00993", "001649", "00688", "00256", "00380", "00971", "0044", "001", "00598", "00998", "00678", "0058", "0084", "001284", "001340", "00681", "00967", "00260", "00263"};
    }

    public CountryTools() {
        this.f3497b = new ArrayList();
        this.f3498c = new ArrayList();
        this.f3499d = new HashMap();
        this.f3500e = new HashMap();
        this.f3501f = new HashMap();
        m4417c();
    }

    public static CountryTools m4416a() {
        if (f3494a == null) {
            f3494a = new CountryTools();
        }
        return f3494a;
    }

    private void m4417c() {
        for (int i = 0; i < f3496h.length; i++) {
            this.f3498c.add(f3495g[i]);
            this.f3497b.add(f3496h[i]);
            this.f3500e.put(f3495g[i], f3496h[i]);
            this.f3499d.put(f3496h[i], f3495g[i]);
            this.f3501f.put(f3496h[i], Integer.valueOf(m4419j(f3496h[i])));
        }
    }

    private String m4418i(String str) {
        String str2 = "";
        for (char c : str.toCharArray()) {
            if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
                str2 = new StringBuilder(String.valueOf(str2)).append(c).toString();
            }
        }
        return str2;
    }

    private int m4419j(String str) {
        return str.equals("0098") ? R.large_flag_ir : str.equals("001") ? R.large_flag_us : str.equals("001242") ? R.large_flag_bs : str.equals("001246") ? R.large_flag_bb : str.equals("001264") ? R.large_flag_al : str.equals("001268") ? R.large_flag_ag : str.equals("001284") ? R.large_flag_vg : str.equals("001340") ? R.large_flag_vi : str.equals("001345") ? R.large_flag_ky : str.equals("001441") ? R.large_flag_bm : str.equals("001473") ? R.large_flag_gd : str.equals("001649") ? R.large_flag_tc : str.equals("001664") ? R.large_flag_ms : str.equals("001670") ? R.large_flag_mp : str.equals("001671") ? R.large_flag_gu : str.equals("001684") ? R.large_flag_as : str.equals("001758") ? R.large_flag_ls : str.equals("001767") ? R.large_flag_dm : str.equals("001784") ? R.large_flag_vc : (str.equals("001787") || str.equals("001939")) ? R.large_flag_pr : (str.equals("001809") || str.equals("001829")) ? R.large_flag_do : str.equals("001868") ? R.large_flag_tt : str.equals("001869") ? R.large_flag_kn : str.equals("001876") ? R.large_flag_jm : str.equals("0020") ? R.large_flag_eg : str.equals("00212") ? R.large_flag_ma : str.equals("00213") ? R.large_flag_dz : str.equals("00216") ? R.large_flag_tn : str.equals("00218") ? R.large_flag_ly : str.equals("00220") ? R.large_flag_gm : str.equals("00221") ? R.large_flag_sn : str.equals("00222") ? R.large_flag_mr : str.equals("00223") ? R.large_flag_ml : str.equals("00224") ? R.large_flag_gn : str.equals("00225") ? R.large_flag_ci : str.equals("00226") ? R.large_flag_bf : str.equals("00227") ? R.large_flag_ne : str.equals("00228") ? R.large_flag_tg : str.equals("00229") ? R.large_flag_bj : str.equals("00230") ? R.large_flag_mu : str.equals("00231") ? R.large_flag_lr : str.equals("00232") ? R.large_flag_sl : str.equals("00233") ? R.large_flag_gh : str.equals("00234") ? R.large_flag_ng : str.equals("00235") ? R.large_flag_td : str.equals("00236") ? R.large_flag_cf : str.equals("00237") ? R.large_flag_cm : str.equals("00238") ? R.large_flag_cv : str.equals("00239") ? R.large_flag_st : str.equals("00240") ? R.large_flag_gq : str.equals("00241") ? R.large_flag_ga : str.equals("00243") ? R.large_flag_cd : str.equals("00244") ? R.large_flag_ao : str.equals("00245") ? R.large_flag_gw : str.equals("00246") ? R.large_flag_io : str.equals("00247") ? R.large_flag_uk : str.equals("00248") ? R.large_flag_sc : str.equals("00249") ? R.large_flag_sd : str.equals("00250") ? R.large_flag_rw : str.equals("00251") ? R.large_flag_et : str.equals("00252") ? R.large_flag_so : str.equals("00253") ? R.large_flag_dj : str.equals("00254") ? R.large_flag_ke : str.equals("00255") ? R.large_flag_tz : str.equals("00256") ? R.large_flag_ug : str.equals("00257") ? R.large_flag_bi : str.equals("00258") ? R.large_flag_mz : str.equals("00260") ? R.large_flag_zm : str.equals("00261") ? R.large_flag_mg : str.equals("00262") ? R.large_flag_re : str.equals("00263") ? R.large_flag_zw : str.equals("00264") ? R.large_flag_na : str.equals("00265") ? R.large_flag_mw : str.equals("00266") ? R.large_flag_ls : str.equals("00267") ? R.large_flag_bw : str.equals("00268") ? R.large_flag_sz : str.equals("00269") ? R.large_flag_km : str.equals("0027") ? R.large_flag_za : str.equals("00290") ? R.large_flag_sh : str.equals("00291") ? R.large_flag_er : str.equals("00297") ? R.large_flag_aw : str.equals("00298") ? R.large_flag_fo : str.equals("00299") ? R.large_flag_gl : str.equals("0030") ? R.large_flag_gr : str.equals("0031") ? R.large_flag_nl : str.equals("0032") ? R.large_flag_be : str.equals("0033") ? R.large_flag_fr : str.equals("0034") ? R.large_flag_es : str.equals("00350") ? R.large_flag_gi : str.equals("00351") ? R.large_flag_pt : str.equals("00352") ? R.large_flag_lu : str.equals("00353") ? R.large_flag_ie : str.equals("00354") ? R.large_flag_is : str.equals("00355") ? R.large_flag_al : str.equals("00356") ? R.large_flag_mt : str.equals("00357") ? R.large_flag_cy : str.equals("00358") ? R.large_flag_fi : str.equals("00359") ? R.large_flag_bg : str.equals("0036") ? R.large_flag_hu : str.equals("00370") ? R.large_flag_lt : str.equals("00371") ? R.large_flag_lv : str.equals("00372") ? R.large_flag_ee : str.equals("00373") ? R.large_flag_md : str.equals("00374") ? R.large_flag_am : str.equals("00375") ? R.large_flag_by : str.equals("00376") ? R.large_flag_ad : str.equals("00377") ? R.large_flag_mc : str.equals("00378") ? R.large_flag_sm : str.equals("00380") ? R.large_flag_ua : str.equals("00381") ? R.large_flag_rs : str.equals("00382") ? R.large_flag_me : str.equals("00385") ? R.large_flag_hr : str.equals("00386") ? R.large_flag_si : str.equals("00387") ? R.large_flag_ba : str.equals("00389") ? R.large_flag_mk : str.equals("0039") ? R.large_flag_it : str.equals("0040") ? R.large_flag_ro : str.equals("0041") ? R.large_flag_ch : str.equals("00420") ? R.large_flag_cz : str.equals("00421") ? R.large_flag_sk : str.equals("00423") ? R.large_flag_li : str.equals("0043") ? R.large_flag_at : str.equals("0044") ? R.large_flag_uk : str.equals("0045") ? R.large_flag_dk : str.equals("0046") ? R.large_flag_se : str.equals("0047") ? R.large_flag_no : str.equals("0048") ? R.large_flag_pl : str.equals("0049") ? R.large_flag_de : str.equals("00500") ? R.large_flag_fk : str.equals("00501") ? R.large_flag_bz : str.equals("00502") ? R.large_flag_gt : str.equals("00503") ? R.large_flag_sv : str.equals("00504") ? R.large_flag_hn : str.equals("00505") ? R.large_flag_ni : str.equals("00506") ? R.large_flag_cr : str.equals("00507") ? R.large_flag_pa : str.equals("00508") ? R.large_flag_pm : str.equals("00509") ? R.large_flag_ht : str.equals("0051") ? R.large_flag_pe : str.equals("0052") ? R.large_flag_mx : str.equals("0053") ? R.large_flag_cu : str.equals("0054") ? R.large_flag_ar : str.equals("0055") ? R.large_flag_br : str.equals("0056") ? R.large_flag_cl : str.equals("0057") ? R.large_flag_co : str.equals("0058") ? R.large_flag_ve : str.equals("00590") ? R.large_flag_gp : str.equals("00591") ? R.large_flag_bo : str.equals("00592") ? R.large_flag_gy : str.equals("00593") ? R.large_flag_ec : str.equals("00594") ? R.large_flag_gf : str.equals("00595") ? R.large_flag_py : str.equals("00596") ? R.large_flag_mq : str.equals("00597") ? R.large_flag_sr : str.equals("00598") ? R.large_flag_uy : str.equals("00599") ? R.large_flag_nl : str.equals("0060") ? R.large_flag_my : str.equals("0061") ? R.large_flag_au : str.equals("00618") ? R.large_flag_cx : str.equals("0062") ? R.large_flag_id : str.equals("0063") ? R.large_flag_ph : str.equals("0064") ? R.large_flag_nz : str.equals("0065") ? R.large_flag_sg : str.equals("0066") ? R.large_flag_th : str.equals("00670") ? R.large_flag_tl : str.equals("006721") ? R.large_flag_aq : str.equals("006723") ? R.large_flag_nf : str.equals("00673") ? R.large_flag_bn : str.equals("00674") ? R.large_flag_nr : str.equals("00675") ? R.large_flag_pg : str.equals("00676") ? R.large_flag_to : str.equals("00677") ? R.large_flag_sb : str.equals("00678") ? R.large_flag_vu : str.equals("00679") ? R.large_flag_fj : str.equals("00680") ? R.large_flag_pw : str.equals("00681") ? R.large_flag_wf : str.equals("00682") ? R.large_flag_ck : str.equals("00683") ? R.large_flag_nu : str.equals("00685") ? R.large_flag_ws : str.equals("00686") ? R.large_flag_ki : str.equals("00687") ? R.large_flag_nc : str.equals("00688") ? R.large_flag_tv : str.equals("00689") ? R.large_flag_pf : str.equals("00690") ? R.large_flag_tk : str.equals("00691") ? R.large_flag_fm : str.equals("00692") ? R.large_flag_mh : str.equals("007") ? R.large_flag_ru : str.equals("0077") ? R.large_flag_kz : str.equals("0081") ? R.large_flag_jp : str.equals("0082") ? R.large_flag_kr : str.equals("0084") ? R.large_flag_vn : str.equals("00850") ? R.large_flag_kp : str.equals("00852") ? R.large_flag_hk : str.equals("00853") ? R.large_flag_mo : str.equals("00855") ? R.large_flag_kh : str.equals("00856") ? R.large_flag_la : str.equals("0086") ? R.large_flag_cn : str.equals("00880") ? R.large_flag_bd : str.equals("00886") ? R.large_flag_tw : str.equals("0090") ? R.large_flag_tr : str.equals("0091") ? R.large_flag_in : str.equals("0092") ? R.large_flag_pk : str.equals("0093") ? R.large_flag_af : str.equals("0094") ? R.large_flag_lk : str.equals("0095") ? R.large_flag_mm : str.equals("00960") ? R.large_flag_mv : str.equals("00961") ? R.large_flag_lb : str.equals("00962") ? R.large_flag_jo : str.equals("00963") ? R.large_flag_sy : str.equals("00964") ? R.large_flag_iq : str.equals("00965") ? R.large_flag_kw : str.equals("00966") ? R.large_flag_sa : str.equals("00967") ? R.large_flag_ye : str.equals("00968") ? R.large_flag_om : str.equals("00970") ? R.large_flag_ps : str.equals("00971") ? R.large_flag_ae : str.equals("00972") ? R.large_flag_il : str.equals("00973") ? R.large_flag_bh : str.equals("00974") ? R.large_flag_qa : str.equals("00975") ? R.large_flag_bt : str.equals("00976") ? R.large_flag_mn : str.equals("00977") ? R.large_flag_np : str.equals("00992") ? R.large_flag_tj : str.equals("00993") ? R.large_flag_tm : str.equals("00994") ? R.large_flag_az : str.equals("00995") ? R.large_flag_ge : str.equals("00996") ? R.large_flag_kg : str.equals("00998") ? R.large_flag_uz : -1;
    }

    public String m4420a(Context context, String str) throws NumberPhoneFormatExeption {
        if (!str.matches("[0-9]+")) {
            throw new NumberPhoneFormatExeption(1);
        } else if (str.length() >= 8) {
            return m4422b(context, str);
        } else {
            throw new NumberPhoneFormatExeption(2);
        }
    }

    public String m4421a(String str) {
        if (!str.startsWith("00")) {
            str = "00" + str;
        }
        String str2 = "";
        for (String str3 : this.f3497b) {
            if (str.startsWith(str3) && str2.length() < str3.length()) {
                str2 = str3;
            }
        }
        return str2.equals("") ? null : str2;
    }

    public String m4422b(Context context, String str) {
        if (str.startsWith("+")) {
            str = str.replaceFirst("\\+", "00");
        }
        String i = m4418i(str);
        if (i.startsWith("00")) {
            return i;
        }
        if (i.startsWith("0")) {
            i = i.replaceFirst("0", "");
        }
        return new StringBuilder(String.valueOf(AppSettings.m4867a(context).m4905o())).append(i).toString();
    }

    public List<String> m4423b() {
        return this.f3498c;
    }

    public void m4424b(String str) throws NumberPhoneFormatExeption {
        if (!str.matches("[0-9]+")) {
            throw new NumberPhoneFormatExeption(1);
        } else if (str.length() < 10) {
            throw new NumberPhoneFormatExeption(2);
        }
    }

    public String m4425c(Context context, String str) {
        String b = m4422b(context, str);
        return b.startsWith("00") ? b.replaceFirst("00", "") : b;
    }

    public String m4426c(String str) {
        if (str == null) {
            return null;
        }
        Object obj;
        if (!str.startsWith("00")) {
            obj = "00" + str;
        }
        return this.f3499d.containsKey(obj) ? (String) this.f3499d.get(obj) : null;
    }

    public String m4427d(Context context, String str) {
        String b = m4422b(context, str);
        return b.startsWith("00") ? b.replaceFirst("00", "") : b;
    }

    public String m4428d(String str) {
        return this.f3500e.containsKey(str) ? (String) this.f3500e.get(str) : null;
    }

    public int m4429e(String str) {
        Object obj;
        if (!str.startsWith("00")) {
            obj = "00" + str;
        }
        return this.f3501f.containsKey(obj) ? ((Integer) this.f3501f.get(obj)).intValue() : -1;
    }

    public String m4430e(Context context, String str) {
        String b = m4422b(context, str);
        return b.startsWith("00") ? b.replaceFirst("00", "") : b;
    }

    public String m4431f(Context context, String str) {
        String b = m4422b(context, str);
        if (b.startsWith("00")) {
            b = b.replaceFirst("00", "");
        }
        return "999" + b;
    }

    public boolean m4432f(String str) {
        return str.startsWith("00999") || str.startsWith("999");
    }

    public String m4433g(Context context, String str) {
        String toUpperCase = str.toUpperCase();
        try {
            Document parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(context.getResources().openRawResource(R.iso_itu));
            parse.getDocumentElement().normalize();
            NodeList elementsByTagName = parse.getElementsByTagName("ISO");
            for (int i = 0; i < elementsByTagName.getLength(); i++) {
                Node item = elementsByTagName.item(i);
                if (item.getNodeType() == (short) 1) {
                    Element element = (Element) item;
                    if (element.getAttribute(XHTMLText.CODE).equals(toUpperCase)) {
                        return "00" + element.getElementsByTagName("ITU").item(0).getTextContent();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String m4434g(String str) {
        if (!str.startsWith("00")) {
            str = "00" + str;
        }
        return str.startsWith("00999") ? str.replaceFirst("00999", "00") : str;
    }

    public String m4435h(String str) {
        return !str.startsWith("00") ? "00" + str : str;
    }
}

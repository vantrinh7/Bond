package com.example.demouser.bond.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
            addItem(createDummyItem(1, "Asking for an informational interview"));
            addItem(createDummyItem(2, "Follow up email after after a networking event"));
            addItem(createDummyItem(3, "Follow up email after submitting an application"));
            addItem(createDummyItem(4, "Follow up email after interview"));
            addItem(createDummyItem(5, "Accepting the offer"));
            addItem(createDummyItem(6, "Declining the offer"));


    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position, String content) {
        return new DummyItem(String.valueOf(position), content, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        if (position == 1) {
            builder.append("Subject Line: Junior at XXX College Asking for Advice" + "\n" +
                    "Body: " + "\n" +
                    "Dear [_______]" + "\n" +
                    "I hope this email finds you well. As a XXX University student, I found you through the alumni feature on LinkedIn and want to ask if you might have some time to share with me information about your background and current role at XXX." + "\n" +
                            "\n" +
                    "I am currently in my third year and majoring in XXX. I am really interested in learning more about working in XXX versus YYY and see from your profile that you have experience in both. I would love to hear your insights and advice. May I have 15-20 minutes of your time over the next few weeks to ask a few questions? I understand that you are busy and appreciate any time you can spare." + "\n" + "\n" +
                    "Thank you and I look forward to hearing from you soon." +
                    "\n" +
                    "Sincerely," + "\n" + "\n" +
                    "[Name]")
            ;
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}

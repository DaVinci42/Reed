package io.github.davinci42.seed.Database;

/**
 * Created by davinci42 on 15/11/16.
 */
public class FeedDbSchema {

    public static final class FeedTable {
        public static final String NAME = "FeedlyFeed";
    }

    public static final class Cols {

        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String CATEGORYLabel = "categoryLabel";
        public static final String CATEGORYId = "categoryId";

        // some feed may not have an icon url
        public static final String ICONURL = "iconUrl";

    }
}

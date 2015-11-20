package io.github.davinci42.seed.Database;

/**
 * Created by davinci42 on 15/11/16.
 */
public class EntryDbSchema {

    public static final class EntryTable {
        public static final String NAME = "UnreadEntry";
    }

    public static final class Cols {

        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String AUTHOR = "author";

        public static final String UPDATED = "updated";

        // StreamId in Entry Entity
        public static final String FEEDID = "feedId";

        // some Entry may not have content
        public static final String CONTENT = "content";

    }

}

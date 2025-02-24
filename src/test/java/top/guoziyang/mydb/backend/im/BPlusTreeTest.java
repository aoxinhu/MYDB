package top.guoziyang.mydb.backend.im;

import java.io.File;
import java.util.List;

import org.junit.Test;

import top.aoxinhu.mydb.backend.dm.DataManager;
import top.aoxinhu.mydb.backend.dm.pageCache.PageCache;
import top.aoxinhu.mydb.backend.im.BPlusTree;
import top.aoxinhu.mydb.backend.tm.TransactionManager;
import top.guoziyang.mydb.backend.tm.MockTransactionManager;

public class BPlusTreeTest {
    @Test
    public void testTreeSingle() throws Exception {
        TransactionManager tm = new MockTransactionManager();
        DataManager dm = DataManager.create("/tmp/TestTreeSingle", PageCache.PAGE_SIZE*10, tm);

        long root = BPlusTree.create(dm);
        BPlusTree tree = BPlusTree.load(root, dm);

        int lim = 10000;
        for(int i = lim-1; i >= 0; i --) {
            tree.insert(i, i);
        }

        for(int i = 0; i < lim; i ++) {
            List<Long> uids = tree.search(i);
            assert uids.size() == 1;
            assert uids.get(0) == i;
        }

        assert new File("/tmp/TestTreeSingle.db").delete();
        assert new File("/tmp/TestTreeSingle.log").delete();
    }
}

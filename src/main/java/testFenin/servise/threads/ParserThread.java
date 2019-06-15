package testFenin.servise.threads;

import testFenin.entity.Post;
import testFenin.servise.DAOImp.PostService;
import testFenin.servise.fileReader.GetURL;
import testFenin.servise.parser.ParserToGetPOST;
import testFenin.servise.parser.ParserToGetURL;

public class ParserThread implements Runnable {
    private Long id;

    public ParserThread(Long id) {
        this.id = id;
    }

    public ParserThread() {
    }

    @Override
    public void run() {
        try {
            Post post = ParserToGetPOST.get().parse(ParserToGetURL.get().getPostURL(GetURL.get().getURL().get(Math.toIntExact(id))));
            if (!PostService.get().getAll().contains(post)) {
                PostService.get().add(post);
            }

        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("Thread №" + id + " have no data");
        }
    }
}

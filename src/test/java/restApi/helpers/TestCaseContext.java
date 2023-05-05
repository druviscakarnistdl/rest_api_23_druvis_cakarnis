package restApi.helpers;

import restApi.domain.Folder;
import restApi.domain.List;
import lombok.Getter;
import lombok.Setter;
import restApi.domain.Tasks;

public class TestCaseContext {
    @Setter @Getter
    private static Folder folder;

    @Setter @Getter
    private static List list;

    @Setter @Getter
    private static Tasks tasks;

    public static void init() {
        folder = null;
        list = null;
        tasks = null;
    }
}

package generalpreps;

public class GeneralPreps {

    public void prepOfStandard(String file, String outfile, String outfile2, String standard) {
        SemiColon semiColon = new SemiColon();
        semiColon.semiColon(file, outfile);
        Removal removal = new Removal();
        removal.remSchSysAndSeas(outfile, outfile2);
        HeadersAdd headersAdd = new HeadersAdd();
        headersAdd.headersAdd(standard);
        InfAdd infAdd = new InfAdd();
        infAdd.addLnCdForStd(outfile2, standard);
        cleanUp(outfile, outfile2);
    }

    public void prepOfSeasonal(String file, String outfile, String outfile2, String seasonal) {
        SemiColon semiColon = new SemiColon();
        semiColon.semiColon(file, outfile);
        Removal removal = new Removal();
        removal.remSchSysAndStd(outfile, outfile2);
        HeadersAdd headersAdd = new HeadersAdd();
        headersAdd.headersAdd(seasonal);
        InfAdd infAdd = new InfAdd();
        infAdd.addLnCdForSeas(outfile2, seasonal);
        cleanUp(outfile, outfile2);
    }

    public void prepOfAll(String file, String outfile, String outfile2, String both) {
        SemiColon semiColon = new SemiColon();
        semiColon.semiColon(file, outfile);
        Removal removal = new Removal();
        removal.remSchSysAndStd(outfile, outfile2);
        HeadersAdd headersAdd = new HeadersAdd();
        headersAdd.headersAdd(both);
        InfAdd infAdd = new InfAdd();
        infAdd.addLnCdForSeas(outfile2, both);
        cleanUp(outfile, outfile2);
        semiColon.semiColon(file, outfile);
        removal.remSchSysAndSeas(outfile, outfile2);
        infAdd.addLnCdForStd(outfile2, both);
        cleanUp(outfile, outfile2);
    }

    public void cleanUp(String outfile, String outfile2) {
        CleanUp cleanUp = new CleanUp();
        cleanUp.cleanUp(outfile, outfile2);
    }
}
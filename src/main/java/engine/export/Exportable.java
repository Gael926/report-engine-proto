package engine.export;
import engine.model.Report;

    public interface Exportable {
        String export(Report report);
        String getFormat();
    }

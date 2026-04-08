package engine.export;

import engine.model.DataRecord;
import engine.model.Report;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class CsvExporter implements Exportable {

    public CsvExporter() {

    }

    public String export(Report report) {
        if (report.getRecordCount() == 0) {
            return "";
        } else {
            // Pour manipuler plus facilement les String
            StringBuilder sb = new StringBuilder();

            // On récupère la 1ère ligne pour récupérer les colonnes
            DataRecord firstRecord = report.getRecords().get(0);

            // On récupère les étiquettes
            Set<String> key = firstRecord.getKeys();

            String header = String.join(",", key);
            sb.append(header).append("\n");

            for (int i = 0; i < report.getRecordCount(); i++) {
                DataRecord recordAct = report.getRecords().get(i);

                // Ligne temp pour stocker les valeurs de cette ligne
                List<String> valeursLigne = new ArrayList<>();

                for (String cle : key) {
                    // On récupère la valeur brute (ex: 100.0 ou "Pizza")
                    Object valueBrut = recordAct.getValue(cle);
                    // On transforme l'objet en String et on l'ajoute à notre liste de la ligne
                    valeursLigne.add(String.valueOf(valueBrut));
                }

                String textLigne = String.join(",", valeursLigne);
                sb.append(textLigne).append("\n");
            }

            return sb.toString();
        }
    }
    public String getFormat() {
        return "CSV";
    }
}

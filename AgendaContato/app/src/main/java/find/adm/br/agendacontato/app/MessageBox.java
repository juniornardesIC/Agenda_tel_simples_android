package find.adm.br.agendacontato.app;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Junior Nardes 03/08/2015.
 */
public class MessageBox {


    public static void show(Context ctx, String title, String msg)
    {
        show(ctx, title, msg, 0);
    }

    public static void show(Context ctx, String title, String msg, int iconId)
    {
        AlertDialog.Builder dlg = new AlertDialog.Builder(ctx);
        dlg.setIcon(iconId);
        dlg.setTitle(title);
        dlg.setMessage(msg);
        dlg.setNeutralButton("OK", null);
        dlg.show();
    }

}

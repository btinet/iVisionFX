package ivisionfx.interaction.gamepad;

import TUIO.*;

public class GamepadListener implements TuioListener {
    @Override
    public void addTuioObject(TuioObject tobj) {
        System.out.printf("Objekt mit Symbol-ID %s hinzugefügt.%n",tobj.getSymbolID());
    }

    @Override
    public void updateTuioObject(TuioObject tobj) {

    }

    @Override
    public void removeTuioObject(TuioObject tobj) {
        System.out.printf("Objekt mit Symbol-ID %s entfernt.%n",tobj.getSymbolID());
    }

    @Override
    public void addTuioCursor(TuioCursor tcur) {
        System.out.printf("Cursor mit Symbol-ID %s hinzugefügt.%n",tcur.getCursorID());
    }

    @Override
    public void updateTuioCursor(TuioCursor tcur) {

    }

    @Override
    public void removeTuioCursor(TuioCursor tcur) {
        System.out.printf("Cursor mit Symbol-ID %s entfernt.%n",tcur.getCursorID());
    }

    @Override
    public void addTuioBlob(TuioBlob tblb) {

    }

    @Override
    public void updateTuioBlob(TuioBlob tblb) {

    }

    @Override
    public void removeTuioBlob(TuioBlob tblb) {

    }

    @Override
    public void refresh(TuioTime ftime) {

    }
}

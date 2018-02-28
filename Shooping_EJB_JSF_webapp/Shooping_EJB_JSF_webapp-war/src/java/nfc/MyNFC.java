package nfc;

import java.util.List;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;

/**
 *
 * @author MAROUANE
 */
public class MyNFC {

    public MyNFC() {
    }

    public String getCardUID(long wait) throws CardException {

        TerminalFactory factory = TerminalFactory.getDefault();
        List<CardTerminal> terminals = factory.terminals().list();
        CardTerminal terminal = terminals.get(0);

        if (terminal.waitForCardPresent(wait)) {
            Card card = terminal.connect("*");
            CardChannel channel = card.getBasicChannel();
            //get UID cmd apdu
            ResponseAPDU res = channel.transmit(new CommandAPDU(new byte[]{(byte) 0xFF, (byte) 0xCA, (byte) 0x00, (byte) 0x00, 0x00}));
            return convertBinToASCII(res.getData());
        }
        return null;
    }

    public static String convertBinToASCII(byte[] bin) {
        return convertBinToASCII(bin, 0, bin.length);
    }

    public static String convertBinToASCII(byte[] bin, int offset, int length) {
        StringBuilder sb = new StringBuilder();
        for (int x = offset; x < offset + length; x++) {
            String s = Integer.toHexString(bin[x]);

            if (s.length() == 1) {
                sb.append('0');
            } else {
                s = s.substring(s.length() - 2);
            }
            sb.append(s);
        }
        return sb.toString().toUpperCase();
    }
}

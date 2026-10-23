package coinclicker.factory;

import coinclicker.model.Upgrade;
import java.util.List;

public interface UpgradeFactory {
    Upgrade create(String id);
    List<Upgrade> createAll();
}

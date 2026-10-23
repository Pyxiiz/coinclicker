package coinclicker.factory;

import coinclicker.model.Upgrade;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class RegistryUpgradeFactory implements UpgradeFactory {
    private final Map<String, Supplier<Upgrade>> creators = new LinkedHashMap<>();

    public void register(String id, Supplier<Upgrade> creator) {
        creators.put(id, creator);
    }

    @Override
    public Upgrade create(String id) {
        Supplier<Upgrade> creator = creators.get(id);
        if (creator == null) throw new IllegalArgumentException("Unknown upgrade: " + id);
        return creator.get();   // fresh instance every call
    }

    @Override
    public List<Upgrade> createAll() {
        List<Upgrade> result = new ArrayList<>();
        creators.values().forEach(c -> result.add(c.get()));
        return result;
    }
}

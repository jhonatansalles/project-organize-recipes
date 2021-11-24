package br.com.vibbra.organizerecipes.service.settings;

import br.com.vibbra.organizerecipes.base.BaseService;
import br.com.vibbra.organizerecipes.exception.NotFoundException;
import br.com.vibbra.organizerecipes.model.entity.settings.Settings;
import br.com.vibbra.organizerecipes.model.response.SettingsResponse;
import br.com.vibbra.organizerecipes.repository.settings.SettingsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class SettingsService extends BaseService {

    @Autowired
    SettingsRepository settingsRepository;

    public SettingsResponse getSettings() {
        Optional<Settings> optionalSettings = settingsRepository.findFirstByOrderById();
        return SettingsResponse.builder()
                .settings(optionalSettings.orElseThrow(() -> new NotFoundException("Settings not found.")))
                .build();
    }

    public SettingsResponse updateSettings(Settings settings) {

        this.validateRequiredFields(this.returnMapRequiredFields(settings));

        Settings settingsBD = settingsRepository.findFirstByOrderById().orElse(null);
        if (Objects.isNull(settingsBD)) {
            settings.setUserLastchange("API_CREATE_SETTINGS");
        } else {
            settings.setId(settingsBD.getId());
            settings.setVersion(settingsBD.getVersion());
            settings.setUserLastchange("API_UPDATE_SETTINGS");
        }

        settings = settingsRepository.save(settings);

        return SettingsResponse.builder()
                .settings(settings)
                .build();
    }

    private Map<String, Optional<?>> returnMapRequiredFields(Settings object) {
        return Map.of(
                "Max Revenue Amount", Optional.ofNullable(object).map(Settings::getMaxRevenueAmount),
                "Sms Notification", Optional.ofNullable(object).map(Settings::getSmsNotification),
                "Email Notification", Optional.ofNullable(object).map(Settings::getEmailNotification)
        );
    }
}

package br.com.vibbra.organizerecipes.repository.settings;

import br.com.vibbra.organizerecipes.model.entity.settings.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long> {

    @Transactional(readOnly = true)
    Optional<Settings> findFirstByOrderById();
}
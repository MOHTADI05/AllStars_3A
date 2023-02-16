<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230212110925 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE composition (id INT AUTO_INCREMENT NOT NULL, programe_regime_id INT DEFAULT NULL, aliment_id INT DEFAULT NULL, ordre_consommation INT NOT NULL, INDEX IDX_C7F4347A4849A05 (programe_regime_id), INDEX IDX_C7F4347415B9F11 (aliment_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE composition ADD CONSTRAINT FK_C7F4347A4849A05 FOREIGN KEY (programe_regime_id) REFERENCES regime (id)');
        $this->addSql('ALTER TABLE composition ADD CONSTRAINT FK_C7F4347415B9F11 FOREIGN KEY (aliment_id) REFERENCES aliment (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE composition DROP FOREIGN KEY FK_C7F4347A4849A05');
        $this->addSql('ALTER TABLE composition DROP FOREIGN KEY FK_C7F4347415B9F11');
        $this->addSql('DROP TABLE composition');
    }
}

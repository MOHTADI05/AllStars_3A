<?php

namespace App\Entity;

use App\Repository\CompositionRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: CompositionRepository::class)]
class Composition
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\ManyToOne(inversedBy: 'compositions')]
    private ?Regime $programeRegime = null;

    #[ORM\ManyToOne(inversedBy: 'compositions')]
    private ?Aliment $aliment = null;

    #[ORM\Column]
    private ?int $ordreConsommation = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getProgrameRegime(): ?Regime
    {
        return $this->programeRegime;
    }

    public function setProgrameRegime(?Regime $programeRegime): self
    {
        $this->programeRegime = $programeRegime;

        return $this;
    }

    public function getAliment(): ?Aliment
    {
        return $this->aliment;
    }

    public function setAliment(?Aliment $aliment): self
    {
        $this->aliment = $aliment;

        return $this;
    }

    public function getOrdreConsommation(): ?int
    {
        return $this->ordreConsommation;
    }

    public function setOrdreConsommation(int $ordreConsommation): self
    {
        $this->ordreConsommation = $ordreConsommation;

        return $this;
    }
}

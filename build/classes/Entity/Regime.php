<?php

namespace App\Entity;

use App\Repository\RegimeRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: RegimeRepository::class)]
class Regime
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 20)]
    private ?string $name = null;

    #[ORM\Column(length: 255)]
    private ?string $discription = null;

    #[ORM\Column(type: Types::DATE_MUTABLE)]
    private ?\DateTimeInterface $dDate = null;

    #[ORM\Column]
    private ?int $nb_calorie = null;

    #[ORM\Column(type: Types::TIME_MUTABLE)]
    private ?\DateTimeInterface $duree = null;
    
    //#[ORM\OneToMany(mappedBy: 'regime', targetEntity: Composition::class)]
    //private Collection $compositions;
    
    
    #[ORM\Column(length: 255)]
    private ?string $image = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(string $name): self
    {
        $this->name = $name;

        return $this;
    }

    public function getDiscription(): ?string
    {
        return $this->discription;
    }


    public function setDiscription(string $discription): self
    {
        $this->discription = $discription;

        return $this;
    }

    public function getDDate(): ?\DateTimeInterface
    {
        return $this->dDate;
    }

    public function setDDate(\DateTimeInterface $dDate): self
    {
        $this->dDate = $dDate;

        return $this;
    }

    public function getNbCalorie(): ?int
    {
        return $this->nb_calorie;
    }

    public function setNbCalorie(int $nb_calorie): self
    {
        $this->nb_calorie = $nb_calorie;

        return $this;
    }

    public function getDuree(): ?\DateTimeInterface
    {
        return $this->duree;
    }

    public function setDuree(\DateTimeInterface $duree): self
    {
        $this->duree = $duree;

        return $this;
    }
    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(?string $image): self
    {
        $this->image = $image;

        return $this;
    }

}

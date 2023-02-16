<?php

namespace App\Entity;

use App\Repository\AlimentRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: AlimentRepository::class)]
class Aliment
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    private ?string $Nom = null;

    //#[ORM\Column(length: 255)]
    //private ?string $Categorie = null;

    #[ORM\Column]
    private ?int $nbCalories = null;

    #[ORM\Column(length: 255)]
    private ?string $Informations_nutritionnelles = null;

    #[ORM\OneToMany(mappedBy: 'aliment', targetEntity: Composition::class)]
    private Collection $compositions;

    public function __construct()
    {
        $this->compositions = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->Nom;
    }

    public function setNom(string $Nom): self
    {
        $this->Nom = $Nom;

        return $this;
    }

    /*public function getCatégorie(): ?string
    {
        return $this->Categorie;
    }

    public function setCatégorie(string $Catégorie): self
    {
        $this->Categorie = $Catégorie;

        return $this;
    }*/

    public function getNbCalories(): ?int
    {
        return $this->nbCalories;
    }

    public function setNbCalories(int $nbCalories): self
    {
        $this->nbCalories = $nbCalories;

        return $this;
    }

    public function getInformationsNutritionnelles(): ?string
    {
        return $this->Informations_nutritionnelles;
    }

    public function setInformationsNutritionnelles(string $Informations_nutritionnelles): self
    {
        $this->Informations_nutritionnelles = $Informations_nutritionnelles;

        return $this;
    }

    /**
     * @return Collection<int, Composition>
     */
    public function getCompositions(): Collection
    {
        return $this->compositions;
    }

    public function addComposition(Composition $composition): self
    {
        if (!$this->compositions->contains($composition)) {
            $this->compositions->add($composition);
            $composition->setAliment($this);
        }

        return $this;
    }

    public function removeComposition(Composition $composition): self
    {
        if ($this->compositions->removeElement($composition)) {
            // set the owning side to null (unless already changed)
            if ($composition->getAliment() === $this) {
                $composition->setAliment(null);
            }
        }

        return $this;
    }
}

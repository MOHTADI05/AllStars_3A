<?php

namespace App\Controller;

use App\Entity\Regime;
use App\Form\RegimeType;
use App\Repository\RegimeRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\Exception\FileException;

#[Route('/regime')]
class RegimeController extends AbstractController
{
    #[Route('/', name: 'app_regime_index', methods: ['GET'])]
    public function index(RegimeRepository $regimeRepository): Response
    {
        return $this->render('regime/index.html.twig', [
            'regimes' => $regimeRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_regime_new', methods: ['GET', 'POST'])]
    public function new(Request $request, RegimeRepository $regimeRepository): Response
    {
        $regime = new Regime();
        $form = $this->createForm(RegimeType::class, $regime);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $image = $form->get('image')->getData();
            if ($image) {
                $originalFilename = pathinfo($image->getClientOriginalName(), PATHINFO_FILENAME);
                // this is needed to safely include the file name as part of the URL
                $newFilename = $originalFilename.'-'.uniqid().'.'.$image->guessExtension();

                // Move the file to the directory where brochures are stored
                try {
                    $image->move(
                        $this->getParameter('images_directory'),
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }

                // updates the 'brochureFilename' property to store the PDF file name
                // instead of its contents
                $regime->setimage($newFilename);
            }
            $regimeRepository->save($regime, true);

            return $this->redirectToRoute('app_regime_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('regime/new.html.twig', [
            'regime' => $regime,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_regime_show', methods: ['GET'])]
    public function show(Regime $regime): Response
    {
        return $this->render('regime/show.html.twig', [
            'regime' => $regime,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_regime_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Regime $regime, RegimeRepository $regimeRepository): Response
    {
        $form = $this->createForm(RegimeType::class, $regime);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $regimeRepository->save($regime, true);

            return $this->redirectToRoute('app_regime_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('regime/edit.html.twig', [
            'regime' => $regime,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_regime_delete', methods: ['POST'])]
    public function delete(Request $request, Regime $regime, RegimeRepository $regimeRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$regime->getId(), $request->request->get('_token'))) {
            $regimeRepository->remove($regime, true);
        }

        return $this->redirectToRoute('app_regime_index', [], Response::HTTP_SEE_OTHER);
    }
}

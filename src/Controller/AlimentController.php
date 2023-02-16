<?php

namespace App\Controller;

use App\Entity\Aliment;
use App\Form\AlimentType;
use App\Repository\AlimentRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/aliment')]
class AlimentController extends AbstractController
{
    #[Route('/', name: 'app_aliment_index', methods: ['GET'])]
    public function index(AlimentRepository $alimentRepository): Response
    {
        return $this->render('aliment/index.html.twig', [
            'aliments' => $alimentRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_aliment_new', methods: ['GET', 'POST'])]
    public function new(Request $request, AlimentRepository $alimentRepository): Response
    {
        $aliment = new Aliment();
        $form = $this->createForm(AlimentType::class, $aliment);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $alimentRepository->save($aliment, true);

            return $this->redirectToRoute('app_aliment_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('aliment/new.html.twig', [
            'aliment' => $aliment,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_aliment_show', methods: ['GET'])]
    public function show(Aliment $aliment): Response
    {
        return $this->render('aliment/show.html.twig', [
            'aliment' => $aliment,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_aliment_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Aliment $aliment, AlimentRepository $alimentRepository): Response
    {
        $form = $this->createForm(AlimentType::class, $aliment);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $alimentRepository->save($aliment, true);

            return $this->redirectToRoute('app_aliment_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('aliment/edit.html.twig', [
            'aliment' => $aliment,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_aliment_delete', methods: ['POST'])]
    public function delete(Request $request, Aliment $aliment, AlimentRepository $alimentRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$aliment->getId(), $request->request->get('_token'))) {
            $alimentRepository->remove($aliment, true);
        }

        return $this->redirectToRoute('app_aliment_index', [], Response::HTTP_SEE_OTHER);
    }
}

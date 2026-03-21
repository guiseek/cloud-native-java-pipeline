import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';

import {
  PessoaCreateRequest,
  PessoaResumo,
  PessoaService,
  PessoaUpdateRequest,
} from '../../core/pessoa.service';

@Component({
  selector: 'app-pessoas',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './pessoas.html',
  styleUrl: './pessoas.css',
})
export class PessoasComponent implements OnInit {
  private readonly pessoaService = inject(PessoaService);
  private readonly fb = inject(FormBuilder);

  pessoas = signal<PessoaResumo[]>([]);
  loading = signal(false);
  saving = signal(false);
  errorMessage = signal('');
  successMessage = signal('');

  editandoId: string | null = null;

  buscaForm = this.fb.group({
    nome: [''],
  });

  form = this.fb.group({
    nome: ['', [Validators.required, Validators.maxLength(150)]],
    cpfCnpj: ['', [Validators.required, Validators.minLength(11), Validators.maxLength(18)]],
    telefonePrincipal: ['', [Validators.maxLength(20)]],
    telefoneSecundario: ['', [Validators.maxLength(20)]],
    email: ['', [Validators.maxLength(150), Validators.email]],
    cep: ['', [Validators.maxLength(9)]],
    endereco: ['', [Validators.maxLength(200)]],
    complemento: ['', [Validators.maxLength(100)]],
    bairro: ['', [Validators.maxLength(100)]],
    cidade: ['', [Validators.maxLength(100)]],
    uf: ['', [Validators.maxLength(2)]],
  });

  ngOnInit(): void {
    this.carregar();
  }

  carregar(): void {
    this.loading.set(true);
    this.errorMessage.set('');

    this.pessoaService.listar().subscribe({
      next: (pessoas) => {
        this.pessoas.set(pessoas);
        this.loading.set(false);
      },
      error: () => {
        this.errorMessage.set('Não foi possível carregar as pessoas.');
        this.loading.set(false);
      },
    });
  }

  pesquisar(): void {
    const nome = (this.buscaForm.value.nome ?? '').trim();

    if (!nome) {
      this.carregar();
      return;
    }

    this.loading.set(true);
    this.errorMessage.set('');

    this.pessoaService.buscarPorNome(nome).subscribe({
      next: (pessoas) => {
        this.pessoas.set(pessoas);
        this.loading.set(false);
      },
      error: () => {
        this.errorMessage.set('Não foi possível buscar as pessoas.');
        this.loading.set(false);
      },
    });
  }

  limparBusca(): void {
    this.buscaForm.reset({ nome: '' });
    this.carregar();
  }

  novo(): void {
    this.editandoId = null;
    this.successMessage.set('');
    this.errorMessage.set('');
    this.form.reset({
      nome: '',
      cpfCnpj: '',
      telefonePrincipal: '',
      telefoneSecundario: '',
      email: '',
      cep: '',
      endereco: '',
      complemento: '',
      bairro: '',
      cidade: '',
      uf: '',
    });
    this.form.get('cpfCnpj')?.enable();
  }

  editar(id: string): void {
    this.loading.set(true);
    this.errorMessage.set('');
    this.successMessage.set('');

    this.pessoaService.buscarPorId(id).subscribe({
      next: (pessoa) => {
        this.editandoId = pessoa.id;
        this.form.reset({
          nome: pessoa.nome ?? '',
          cpfCnpj: pessoa.cpfCnpj ?? '',
          telefonePrincipal: pessoa.telefonePrincipal ?? '',
          telefoneSecundario: pessoa.telefoneSecundario ?? '',
          email: pessoa.email ?? '',
          cep: pessoa.cep ?? '',
          endereco: pessoa.endereco ?? '',
          complemento: pessoa.complemento ?? '',
          bairro: pessoa.bairro ?? '',
          cidade: pessoa.cidade ?? '',
          uf: pessoa.uf ?? '',
        });
        this.form.get('cpfCnpj')?.disable();
        this.loading.set(false);
      },
      error: () => {
        this.errorMessage.set('Não foi possível carregar a pessoa para edição.');
        this.loading.set(false);
      },
    });
  }

  salvar(): void {
    this.errorMessage.set('');
    this.successMessage.set('');

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.saving.set(true);

    if (this.editandoId) {
      const payload: PessoaUpdateRequest = {
        nome: this.form.getRawValue().nome ?? '',
        telefonePrincipal: this.form.getRawValue().telefonePrincipal ?? '',
        telefoneSecundario: this.form.getRawValue().telefoneSecundario ?? '',
        email: this.form.getRawValue().email ?? '',
        cep: this.form.getRawValue().cep ?? '',
        endereco: this.form.getRawValue().endereco ?? '',
        complemento: this.form.getRawValue().complemento ?? '',
        bairro: this.form.getRawValue().bairro ?? '',
        cidade: this.form.getRawValue().cidade ?? '',
        uf: (this.form.getRawValue().uf ?? '').toUpperCase(),
      };

      this.pessoaService.atualizar(this.editandoId, payload).subscribe({
        next: () => {
          this.successMessage.set('Pessoa atualizada com sucesso.');
          this.saving.set(false);
          this.novo();
          this.carregar();
        },
        error: () => {
          this.errorMessage.set('Não foi possível atualizar a pessoa.');
          this.saving.set(false);
        },
      });

      return;
    }

    const payload: PessoaCreateRequest = {
      nome: this.form.getRawValue().nome ?? '',
      cpfCnpj: this.form.getRawValue().cpfCnpj ?? '',
      telefonePrincipal: this.form.getRawValue().telefonePrincipal ?? '',
      telefoneSecundario: this.form.getRawValue().telefoneSecundario ?? '',
      email: this.form.getRawValue().email ?? '',
      cep: this.form.getRawValue().cep ?? '',
      endereco: this.form.getRawValue().endereco ?? '',
      complemento: this.form.getRawValue().complemento ?? '',
      bairro: this.form.getRawValue().bairro ?? '',
      cidade: this.form.getRawValue().cidade ?? '',
      uf: (this.form.getRawValue().uf ?? '').toUpperCase(),
    };

    this.pessoaService.criar(payload).subscribe({
      next: () => {
        this.successMessage.set('Pessoa criada com sucesso.');
        this.saving.set(false);
        this.novo();
        this.carregar();
      },
      error: () => {
        this.errorMessage.set('Não foi possível criar a pessoa.');
        this.saving.set(false);
      },
    });
  }

  excluir(id: string, nome: string): void {
    const confirmou = window.confirm(`Deseja realmente excluir a pessoa "${nome}"?`);

    if (!confirmou) {
      return;
    }

    this.errorMessage.set('');
    this.successMessage.set('');

    this.pessoaService.excluir(id).subscribe({
      next: () => {
        this.successMessage.set('Pessoa excluída com sucesso.');
        if (this.editandoId === id) {
          this.novo();
        }
        this.carregar();
      },
      error: () => {
        this.errorMessage.set('Não foi possível excluir a pessoa.');
      },
    });
  }

  get tituloFormulario(): string {
    return this.editandoId ? 'Editar pessoa' : 'Nova pessoa';
  }
}

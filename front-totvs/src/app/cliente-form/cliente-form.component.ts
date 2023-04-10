import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-cliente-form',
  templateUrl: './cliente-form.component.html',
  styleUrls: ['./cliente-form.component.css']
})
export class ClienteFormComponent {
  nome: string = '';
  endereco: string = '';
  bairro: string = '';
  telefones: string[] = [''];
  successMessage: string = '';

  constructor(private http: HttpClient) {}


  onSubmit() {
    const data = {
      nome: this.nome,
      endereco: this.endereco,
      bairro: this.bairro,
      telefones: this.telefones
    };

    this.http.post('http://localhost:9000/api/clientes', data).subscribe(response => {
      this.successMessage = 'Cliente inserido com sucesso!';
      this.nome = '';
      this.endereco = '';
      this.bairro = '';
      this.telefones = [''];
      console.log(response);
    },
    (error) => {
      console.log(error);
      alert(error.error.join("\n") || 'Ocorreu um erro inesperado');
    });
  }

  addTelefone() {
    this.telefones.push('');
  }

  removeTelefone(index: number) {
    this.telefones.splice(index, 1);  
  }

  trackByFn(index: any, item: any) {
    return index;  
  }


}

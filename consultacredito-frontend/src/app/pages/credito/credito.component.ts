import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { of } from "rxjs";
import { Credito } from "src/app/shared/models/credito.model";
import { CreditoService } from "src/app/shared/services/credito.service";

@Component({
    selector: 'app-credito',
    templateUrl: './credito.component.html',
    styleUrls: ['./credito.component.scss']
})
export class CreditoComponent implements OnInit {

    form?: FormGroup;

    titulo: string = 'Consultar Crédito';

    constructor(private creditoService: CreditoService) { }

    creditos: Credito[] = [];

    ngOnInit(): void {
        this.formInit();
    }

    buscar() {
        if (this.form!.valid) {
            console.log(this.form!.value);
            let numero = this.form!.get("numero")?.value;
            let tipoConsulta = this.form!.get("tipoConsulta")?.value;
            if (tipoConsulta == "nro_nfse") {
                this.creditos = [];
                this.buscarPorNumeroNfse(numero);
            } else if (tipoConsulta == "nro_credito") {
                this.creditos = [];
                this.buscarPorNumeroCredito(numero);
            }
        }
    }

    async buscarPorNumeroNfse(numero: string) {
        of(this.creditoService.buscarPorNumeroNfse(numero).subscribe({
            next: (response) => {
                this.creditos = response;
            },
            error: (e) => {
                alert(e.error ?? "Erro inesperado. Tente novamente.");
            }
        }));
    }

    async buscarPorNumeroCredito(numero: string) {
        of(this.creditoService.buscarPorNumeroCredito(numero).subscribe({
            next: (response) => {
                this.creditos.push(response);
            },
            error: (e) => {
                alert(e.error ?? "Erro inesperado. Tente novamente.");
            }
        }));
    }

    formInit() {
        this.form = new FormGroup({
            tipoConsulta: new FormControl('', [Validators.required,]),
            numero: new FormControl('', [
                Validators.required,
                Validators.pattern("^[0-9]*$"),
                Validators.minLength(6),
            ])
        });
    }
}
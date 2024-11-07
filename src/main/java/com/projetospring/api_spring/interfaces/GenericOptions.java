package com.projetospring.api_spring.interfaces;

import java.util.List;

/**Contrato de CRUD
 * T (Type) - Pode ser qualquer coisa = Aluno, Disciplina, Professor... (Geralmente é a camada model)
 * N (Number) - Integer, Double...

 Construção de um contrato que define operações básicas de CRUD que podem ser aplicadas a qualquer tipo de entidade.
 */

//Generica e aceita dois parâmetros: T e N
public interface GenericOptions<T,N> {

    /** Metodo Create
     - Cria uma nova instância do tipo T (Exemplo: novo Aluno)
     */
    public T create(T entity);

    /** Metodo get por ID
     - Metodo para consultar T baseado no identificador N informado
     */
    public T get(N id);

    /**Metodo para retornar lista
     - Retorna uma lista de todas as entidades do tipo T.
     */
    public List<T> get();

    /** Metodo Update
     - Atualiza uma entidade existente com base no seu ID.
     Parâmetros:
        N id (Identificador da entidade que deve ser atualizada);
        T entity (Nova entidade que contém os dados atualizados).
     */
    public T update (N id, T entity);

    /** Metodo Delete
     - Deleta um registro com base no ID.
     */
    public void delete (N id);
}

class Autor {
  final String nome;
  final String email;
  final String descricao;

  Autor({required this.nome, required this.email, required this.descricao});

  factory Autor.fromJson(Map<String, dynamic> json) {
    return Autor(
      nome: json['nome'],
      email: json['email'],
      descricao: json['descricao'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'nome': nome,
      'email': email,
      'descricao': descricao,
    };
  }
}

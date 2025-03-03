import 'package:flutter/material.dart';
import '../models/autor.dart';
import '../services/autor_service.dart';

class AutorController extends ChangeNotifier {
  final AutorService _autorService = AutorService();
  bool _isLoading = false;
  bool _isError = false;
  String? _message;

  bool get isLoading => _isLoading;
  bool get isError => _isError;
  String? get message => _message;

  void setLoading(bool value) {
    _isLoading = value;
    notifyListeners();
  }

  void setError(bool value) {
    _isError = value;
    notifyListeners();
  }

  void setMessage(String? value) {
    _message = value;
    notifyListeners();
  }

  Future<void> criarAutor(String nome, String email, String descricao) async {
    if (descricao.length > 400) {
      setMessage("A descrição não pode ter mais que 400 caracteres");
      notifyListeners();
      return;
    }

    try {
      setLoading(true);
      setError(false);
      setMessage(null);
      notifyListeners();

      Autor novoAutor = Autor(nome: nome, email: email, descricao: descricao);
      var response = await _autorService.criarAutor(novoAutor);

      setLoading(false);
      if (response.success) {
        setMessage(response.message);
      } else {
        setError(true);
        if (response.errors != null && response.errors!.isNotEmpty) {
          setMessage(response.errors!.join('\n'));
        } else {
          setMessage(response.message);
        }
      }
    } catch (e) {
      setError(true);
      setMessage('Erro inesperado. Tente novamente mais tarde.');
    } finally {
      setLoading(false);
    }

    notifyListeners();
  }
}

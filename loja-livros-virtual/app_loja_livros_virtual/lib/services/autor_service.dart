import 'package:dio/dio.dart';

import '../models/autor.dart';
import 'api_response.dart';
import 'api_service.dart';

class AutorService {
  final ApiService _apiService = ApiService();

  Future<ApiResponse> criarAutor(Autor autor) async {
    try {
      final response = await _apiService.post('/v1/autores', autor.toJson());

      if (response.statusCode == 200) {
        return ApiResponse(
          success: true,
          message: 'Autor criado com sucesso',
          data: response.data,
        );
      }

      return ApiResponse(
        success: false,
        message: 'Erro ao criar autor',
        data: response.data,
      );
    } on DioException catch (e) {
      if (e.response != null) {
        if (e.response?.statusCode == 400) {
          final errors = e.response?.data['errors'] as List<dynamic>?;
          if (errors != null) {
            final validationMessages = errors
                .map((error) => error['defaultMessage'] as String)
                .where((message) => message != null)
                .toList();

            return ApiResponse(
              success: false,
              message: 'Erro de validação',
              errors: validationMessages,
              statusCode: 400,
            );
          }
        }

        return ApiResponse(
          success: false,
          message: e.response?.data['message'] ?? 'Erro desconhecido',
          statusCode: e.response?.statusCode,
        );
      }

      return ApiResponse(
        success: false,
        message: 'Erro de conexão: ${e.message}',
        statusCode: e.response?.statusCode,
      );
    }
  }
}

class ApiResponse {
  final bool success;
  final String message;
  final dynamic data;
  final List<String>? errors;
  final int? statusCode;

  ApiResponse({
    required this.success,
    required this.message,
    this.data,
    this.errors,
    this.statusCode,
  });
}

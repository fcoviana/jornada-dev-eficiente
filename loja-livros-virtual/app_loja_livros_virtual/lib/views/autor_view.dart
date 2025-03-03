import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../controllers/autor_controller.dart';

class AutorView extends StatelessWidget {
  final TextEditingController nomeController = TextEditingController();
  final TextEditingController emailController = TextEditingController();
  final TextEditingController descricaoController = TextEditingController();

  AutorView({super.key});

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => AutorController(),
      child: Scaffold(
        appBar: AppBar(title: const Text('Criar Autor')),
        body: Consumer<AutorController>(
          builder: (context, controller, child) {
            return Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  TextField(
                    controller: nomeController,
                    decoration: const InputDecoration(labelText: 'Nome'),
                  ),
                  TextField(
                    controller: emailController,
                    decoration: const InputDecoration(labelText: 'Email'),
                    keyboardType: TextInputType.emailAddress,
                  ),
                  TextField(
                    controller: descricaoController,
                    decoration: const InputDecoration(labelText: 'Descrição'),
                    maxLength: 400,
                    maxLines: 3,
                  ),
                  const SizedBox(height: 20),
                  controller.isLoading
                      ? const Center(child: CircularProgressIndicator())
                      : ElevatedButton(
                          onPressed: () {
                            controller.criarAutor(
                              nomeController.text,
                              emailController.text,
                              descricaoController.text,
                            );
                          },
                          child: const Text('Criar Autor'),
                        ),
                  const SizedBox(height: 10),
                  if (controller.message != null)
                    Text(
                      controller.message!,
                      style: TextStyle(
                        color: !controller.isError ? Colors.green : Colors.red,
                      ),
                    ),
                ],
              ),
            );
          },
        ),
      ),
    );
  }
}

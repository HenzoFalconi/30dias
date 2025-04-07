package com.example.a30days

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.a30days.ui.theme.AppTheme

data class WellnessTask(val day: String, val title: String, val description: String, val imageUrl: String)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isLightTheme by remember { mutableStateOf(true) }

            AppTheme(isLightTheme = isLightTheme) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("30 Dias de Trap BR") },
                            actions = {
                                IconButton(onClick = { isLightTheme = !isLightTheme }) {
                                    AnimatedContent(targetState = isLightTheme) { isLight ->
                                        if (isLight) {
                                            Icon(
                                                Icons.Filled.Brightness4,
                                                contentDescription = "Ativar tema escuro"
                                            )
                                        } else {
                                            Icon(
                                                Icons.Filled.WbSunny,
                                                contentDescription = "Ativar tema claro"
                                            )
                                        }
                                    }
                                }
                            }
                        )
                    }
                ) { paddingValues ->
                    WellnessList(
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}

@Composable
fun WellnessList(modifier: Modifier = Modifier) {
    val tasks = listOf(
        WellnessTask("Dia 1", "Kyan - Mandrake", "Em 'Mandrake', Kyan narra sua trajetória de superação nas ruas, destacando a evolução pessoal, ostentação e a construção da imagem de 'mandrake' — figura respeitada e estilosa da quebrada. Ele mostra que veio da dificuldade, mas hoje vive um novo momento, marcado por luxo e reconhecimento.", "https://i.scdn.co/image/ab67616d0000b273c3eb5743edeb36fd743011da"),
        WellnessTask("Dia 2", "Veigh - Novo Balanço", "'Novo Balanço' é um hino sobre ascensão e conquista. Veigh canta sobre deixar pra trás a vida difícil, focar nos negócios e curtir os frutos do seu trabalho, sempre muito ligado ao estilo, com referências a roupas e marcas de luxo que hoje fazem parte do seu dia a dia.", "https://i.scdn.co/image/ab67616d0000b273ce0947b85c30490447dbbd91"),
        WellnessTask("Dia 3", "Djonga - Hat-Trick", "Nesta faixa, Djonga usa a metáfora do futebol para falar das suas conquistas no rap, comparando seus feitos a um hat-trick — quando um jogador faz três gols em um jogo. Ele celebra sua habilidade com as palavras, confiança, vitórias e permanência no topo do cenário musical.", "https://i.scdn.co/image/ab67616d0000b273b761536cddbe1430634111ee"),
        WellnessTask("Dia 4", "Danzo - O Cenário Certo Para o Teatro Perfeito", "Danzo entrega um som denso e reflexivo sobre a realidade das favelas. Ele mostra que o crime e a violência muitas vezes fazem parte de um 'teatro' construído pela própria sociedade, enquanto os jovens da quebrada tentam sobreviver em meio às dificuldades e ilusões da rua.", "https://cdn-images.dzcdn.net/images/cover/f418a45cf0e388b97490e3ac85f94080/500x500.jpg"),
        WellnessTask("Dia 5", "Matuê - Máquina do Tempo", "'Máquina do Tempo' é um som nostálgico, onde Matuê relembra momentos da infância, dificuldades do passado e conquistas do presente. A música tem um tom emocional, com desejo de voltar no tempo para reviver lembranças boas ou fazer escolhas diferentes.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSm4ENV-trlh7dmBviDNAmbrlgrmhMqsxUW7w&s"),
        WellnessTask("Dia 6", "MC Luanna - Sexto Sentido", "MC Luanna exalta o poder da mulher e sua intuição natural. 'Sexto Sentido' fala sobre confiança, autoestima e sobre não se deixar enganar nas relações, destacando a força feminina e o autocuidado em qualquer situação.", "https://akamai.sscdn.co/uploadfile/letras/albuns/4/6/b/7/2547721737548446.jpg"),
        WellnessTask("Dia 7", "Recayd Mob - Calzone Tapes 3", "Nesta coletânea, a Recayd Mob mostra o auge da ostentação e da vivência de luxo. Os versos retratam festas regadas a bebida, drogas, roupas caras e carros importados, destacando o lifestyle de quem venceu na cena do trap nacional.", "https://i.scdn.co/image/ab67616d0000b273e40025a740d9106e5452fd6b"),
        WellnessTask("Dia 8", "Kyan - Fantástico Mundo da Oakley", "Kyan transporta o ouvinte para o universo da moda periférica, onde a marca Oakley é símbolo de status. Ele descreve a vivência nas ruas, o respeito adquirido e o modo de se vestir como expressão de identidade e força.", "https://i.scdn.co/image/ab67616d0000b273ddb56ad03b5a7d8296962648"),
        WellnessTask("Dia 9", "Danzo - Prioridades", "'Prioridades' traz um Danzo mais introspectivo, refletindo sobre o que realmente importa na vida. Ele aborda a necessidade de buscar dinheiro e respeito, mas sem esquecer dos valores da família, da humildade e das raízes na quebrada.", "https://cdn-images.dzcdn.net/images/cover/f418a45cf0e388b97490e3ac85f94080/500x500.jpg"),
        WellnessTask("Dia 10", "Veigh - Engana Dizendo Que Ama", "Veigh explora as armadilhas dos relacionamentos modernos, abordando as ilusões, mentiras e traições que podem existir por trás das aparências. A música tem um tom de desabafo, misturado com frieza emocional.", "https://i.scdn.co/image/ab67616d0000b273ce0947b85c30490447dbbd91"),
        WellnessTask("Dia 11", "Kayblack - Licor 43", "'Licor 43' mistura romance, sedução e a malícia das ruas. Kayblack fala de uma relação envolvente, marcada por desejo, luxo e ostentação, sempre com seu estilo diferenciado e letras carregadas de melodia.", "https://i.scdn.co/image/ab67616d0000b273797d2da3e25738b4106723ba"),
        WellnessTask("Dia 12", "Matuê - Quer Voar", "Um dos maiores hits do trap nacional, 'Quer Voar' é sobre quebrar barreiras, buscar liberdade, crescer na vida e fugir das limitações da realidade da quebrada. Matuê canta sobre sonhos, ambições e sobre nunca se contentar com pouco.", "https://i.scdn.co/image/ab67616d0000b273e18fdab2c030bd3aed5c2afb"),
        WellnessTask("Dia 13", "Djonga - Leal", "Djonga deixa claro que lealdade vale mais que dinheiro. Ele reforça a importância de manter as raízes, ser verdadeiro com os seus e não se corromper, mesmo com as tentações que o sucesso e o mundo trazem.", "https://i.scdn.co/image/ab67616d0000b273b761536cddbe1430634111ee"),
        WellnessTask("Dia 14", "Danzo - Desfile na Favela", "Danzo mostra que o estilo da quebrada é único. Em 'Desfile na Favela', ele descreve como os jovens se vestem bem, desfilam pelas ruas com autoestima elevada e mostram que também fazem parte do mundo do luxo.", "https://cdn-images.dzcdn.net/images/cover/f418a45cf0e388b97490e3ac85f94080/500x500.jpg"),
        WellnessTask("Dia 15", "Kyan - gucci con tacchini!", "'gucci con tacchini!' é um som que mistura elementos de luxo com referências da quebrada. Kyan mostra que é possível ser mandrake, estiloso e imponente, vestindo de Gucci ou de Sergio Tacchini, duas marcas que simbolizam opostos sociais.", "https://i.scdn.co/image/ab67616d0000b273ddb56ad03b5a7d8296962648"),
        WellnessTask("Dia 16", "Recayd Mob - Flack Jack", "Mais uma faixa onde a Recayd Mob explora o luxo, a ostentação e o consumo de itens caros. Eles mostram a vida de quem superou a pobreza e hoje vive em meio a festas, carros e roupas de grife.", "https://i.scdn.co/image/ab67616d0000b2735d11d2ee6c155175b7d4b568"),
        WellnessTask("Dia 17", "MC Luanna - 44", "MC Luanna traz um som de empoderamento feminino, onde exibe sensualidade, poder e domínio em seus relacionamentos. Ela deixa claro que é dona do próprio jogo e não se submete a ninguém.", "https://lh3.googleusercontent.com/proxy/rEb8WfNgik8mPPhhAjUJbuA3AUQd3ff3Bna2Nzbjfcf2UrGlLS8bPRJF_JoayX601RwZG7EcsqbXmke_29pMT4JjCB2bQfQh3MjB-tNswIytvSotZgQFjWEYXxZd-dVuNTAaXaq0EfCLUFfIyj8jJff25wfjwHT6lRzz"),
        WellnessTask("Dia 18", "Kayblack - Bonjour", "Kayblack solta linhas sobre frieza, postura firme e conquistas. 'Bonjour' mostra o contraste entre a antiga realidade difícil e o novo estilo de vida, com dinheiro, luxo e respeito nas ruas.", "https://i.scdn.co/image/ab67616d0000b273d46a44c24d37ae912c3cff7f"),
        WellnessTask("Dia 19", "Veigh - Foto do Corte", "'Foto do Corte' é um som sobre decepções amorosas e superação. Veigh relata momentos de frieza e amadurecimento após viver experiências que o deixaram mais forte e calculista.", "https://images.genius.com/3e9d4160d0eaad37c3be72e66dd02f09.1000x1000x1.png"),
        WellnessTask("Dia 20", "Kyan - Dia de Baile", "Em 'Dia de Baile', Kyan narra o cotidiano da quebrada em dia de festa. Funk, estilo, ostentação e a valorização da cultura da favela ganham destaque em um som vibrante e representativo.", "https://cdn-images.dzcdn.net/images/cover/e192b22609b48b211f930943fea02e57/0x1900-000000-80-0-0.jpg"),
        WellnessTask("Dia 21", "Veigh - Vida Chique", "Veigh exalta sua nova realidade, mostrando o contraste entre sua antiga vida na quebrada e o presente de luxo e conquistas. A letra enfatiza que tudo que ele tem hoje é fruto do seu esforço, superação e persistência no trap.", "https://i1.sndcdn.com/artworks-nlRBAr8e5ZBFqtQI-WTzXcw-t500x500.jpg"),
        WellnessTask("Dia 22", "Matuê - Groupies", "Matuê aborda o assédio e o interesse das mulheres após o sucesso, explorando a relação entre fama, dinheiro e relacionamentos superficiais. A faixa carrega um tom debochado e mostra o estilo de vida do artista no auge da carreira.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQc2VTBAJIe84GB8lzX8nLUUSQHGx_gQ_Ocg&s"),
        WellnessTask("Dia 23", "Danzo - Podcast", "Danzo celebra as conquistas materiais como símbolo de vitória após sair da dificuldade. A música usa o relógio Rolex como metáfora para marcar o tempo de luta e o momento atual de ostentação e reconhecimento no cenário.", "https://images.genius.com/6d0c651498bf2fd94fa4afe9ce90b97b.1000x1000x1.jpg"),
        WellnessTask("Dia 24", "Djonga - Olho de Tigre", "Djonga usa a figura do 'olho de tigre' para representar força, foco e determinação. A música fala sobre resiliência, postura de vencedor e a mentalidade necessária para vencer dentro e fora da música, mesmo enfrentando preconceito e desafios.", "https://i.scdn.co/image/ab67616d0000b27337ad963467a58bf1f2676630"),
        WellnessTask("Dia 25", "Kyan - Trap de Cria 2", "Kyan retrata o estilo de vida dos jovens da quebrada, que misturam roupas de marca com atitude de rua. A faixa exalta a cultura periférica e a identidade mandrake, mostrando que o estilo dos cria é único e cheio de personalidade.", "https://cdn-images.dzcdn.net/images/cover/74b2ca56975246fe3eebbfd4a9b7dfb0/0x1900-000000-80-0-0.jpg"),
        WellnessTask("Dia 26", "MC Luanna - Botano", "MC Luanna cria um paralelo entre ela mesma e uma sereia: sedutora, forte e livre. A música fala de empoderamento feminino, autoestima e liberdade das mulheres de viverem da forma que quiserem, sem se prender a padrões.", "https://i1.sndcdn.com/artworks-9vjEMYv0DrVtxpIY-auKn9g-t500x500.jpg"),
        WellnessTask("Dia 27", "Kayblack - Preto no Topo", "Kayblack exalta a vitória dos pretos que venceram na vida sem abrir mão de sua essência. A música traz orgulho racial, conquistas materiais e a importância de se manter firme e verdadeiro mesmo depois do sucesso.", "https://akamai.sscdn.co/uploadfile/letras/albuns/6/4/5/0/01670417034.jpg"),
        WellnessTask("Dia 28", "Recayd Mob - Mlks de SP", "Recayd Mob usa a imagem do 'NikeBoy' para representar o jovem estiloso da quebrada, que ostenta roupas de marca como símbolo de status. A música é cheia de referências à moda, consumo e estilo das ruas.", "https://i.scdn.co/image/ab67616d0000b2734d32f0290f0444a5b7fdad1a"),
        WellnessTask("Dia 29", "Veigh - Os Meninos da Nova", "Veigh explora os sentimentos de alguém que já se machucou em relacionamentos e agora vive com o coração blindado. A música tem um tom melancólico, mas também mostra superação e desapego emocional.", "https://i.scdn.co/image/ab67616d0000b273625c9809f2cf96c76e3e8e33"),
        WellnessTask("Dia 30", "Recayd Mob - Plaqtudum", "Mais uma faixa onde a Recayd Mob explora o luxo, a ostentação e o consumo de itens caros. Eles mostram a vida de quem superou a pobreza e hoje vive em meio a festas, carros e roupas de grife.", "https://cdn-images.dzcdn.net/images/cover/271b82973a645c8da5a44310135bc020/500x500.jpg")
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(tasks) { task ->
            WellnessTaskItem(task)
        }
    }
}

@Composable
fun WellnessTaskItem(task: WellnessTask) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .animateContentSize()
            .clickable { expanded = !expanded },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            Text(
                text = task.day,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = task.title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Column {
                    Image(
                        painter = rememberAsyncImagePainter(task.imageUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}
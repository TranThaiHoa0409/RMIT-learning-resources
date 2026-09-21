import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lecture06.DarkModePreferenceScreen
import com.example.lecture06.TaskViewModel

@Composable
fun TaskListScreen(context: Context) {
    // val viewModel = remember { TaskViewModel(context) }
    val viewModel: TaskViewModel = viewModel()

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                DarkModePreferenceScreen()

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.addTask("New Task") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add Task")
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(viewModel.tasks) { task ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(task.title)
                            IconButton(onClick = { viewModel.deleteTask(task.id) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete Task")
                            }
                        }
                    }
                }
            }
        }
    )
}

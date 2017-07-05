package by.nulay.changer.task;

import org.springframework.stereotype.Service;

/**
 * Created by miha on 14.09.2015.
 */
@Service("jobService")
public class TaskServiceR extends TaskServiceHib<Task,Long> implements TaskService<Task,Long> {
}

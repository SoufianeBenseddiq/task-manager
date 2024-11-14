import { Routes } from '@angular/router';
import { TaskListComponent } from './components/task-list/task-list.component';
import { TaskFormComponent } from './components/task-form/task-form.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';

export const routes: Routes = [
    {
        path : "",
        redirectTo  : "tasks",
        pathMatch : "full"
    },{
        path : "tasks",
        component : TaskListComponent
    },{
        path : "taskform",
        component : TaskFormComponent
    },{
        path : "userprofile",
        component : UserProfileComponent
    },
];

#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <errno.h>

#define MAX 256
char *spcl_char = " ()<>&;\n\t";

int args(char* line){
  char parameters[MAX];
    char* curr;
    int count = 0;

    strcpy(parameters, line);

    curr = strtok(parameters, spcl_char);
    while(curr != NULL){
      count++;
      curr = strtok(NULL, spcl_char);
    }
    
    return count;
}


void displayabout(){
  printf("=====================================================\n");
  printf("This custom shell is created by the following people\n");
  printf("Varun Srinivasan\tW1351349\n");
  printf("Sharon Subathran\tW1358689\n");
  printf("=====================================================\n");
}

// check if the command entered is 'about'
void executeabout(char *command){
  if(strcmp(command,"about\n")==0){
    displayabout();
  }
}

int main(){
  char** argv;
    char* arg;
    char line[MAX], temp[MAX], cwd[MAX];
    int pid, argc, count = 0, pipes[2];
    pipe (pipes);
    printf("Welcome to the Shell implemented in C.\n");

    while(1){
      printf("# ");
      count = 0;
      fflush(NULL);
      fgets(line, MAX, stdin);

      executeabout(line);
      argc = args(line);
      argv = malloc(sizeof(char*) * (argc + 1));

      strcpy(temp, line);

      arg = strtok(temp, spcl_char);
      while(arg != NULL){
          argv[count] = (char*) malloc(sizeof(char) * strlen(arg));
          strcpy(argv[count], arg);

          arg = strtok(NULL, spcl_char);
          count++;
      }

      argv[argc] = NULL;

      if(argv[0] == NULL){
          continue;
      }

      if(strcmp(argv[0], "exit") == 0){
          exit(0);
      }

      else if((strcmp(argv[0], "cd") == 0)||(strcmp(argv[0], "dir") == 0)){
          if(chdir(argv[1]) == -1){
            if(argv[1] == NULL){
              if (getcwd(cwd, sizeof(cwd)) != NULL)
                fprintf(stdout, "Current working dir: %s\n", cwd);
            }
        
          } 
      }

      else if(strcmp(argv[0], "clr") == 0) {
        system("clear");
        //system("cls");
      }

      else{
      pid = fork();

        if(pid != 0){
            wait(NULL);
          }

        else{
            if(strstr(line, ">>") != NULL){
                freopen(argv[argc-1], "a", stdout);
                free(argv[argc-1]);
                argv[argc-1] = NULL;
            }

            else if(strstr(line, ">") != NULL){
                freopen(argv[argc-1], "w", stdout);
              free(argv[argc-1]);
              argv[argc-1] = NULL;
            }

            else if(strstr(line, "<") != NULL){
              freopen(argv[argc-1], "r", stdin);
                free(argv[argc-1]);
                argv[argc-1] = NULL;
            }

          else if(strstr(line, "|") != NULL){
          system(line);
        }

          execvp(argv[0], argv);
          exit(0);
          }
      }
    }
  return 0;
}

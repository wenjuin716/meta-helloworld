#include <stdlib.h>
#include <stdio.h>

#define HELLO_WORLD "Hello World!!"

int main(int argc, char **argv){
  printf("[%s] %s\n", __FUNCTION__, HELLO_WORLD);
  return 0;
}

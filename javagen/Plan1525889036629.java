public class Plan1525889036629 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
StartServer("B");
}

}


for (int i = 0; i < 2 ; i++) {
StartServer("A");
}


}
}

public class Plan1571773009894 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

for (int i = 0; i < 5 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
IncreaseTraffic("A");
}

StartServer("C");

}


}
}

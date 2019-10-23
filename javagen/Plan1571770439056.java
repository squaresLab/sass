public class Plan1571770439056 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("A");

}

for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

}

StartServer("C");
StartServer("B");


}


}
}

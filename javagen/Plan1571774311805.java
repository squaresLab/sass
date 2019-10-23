public class Plan1571774311805 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

StartServer("B");
for (int i = 0; i < 4 ; i++) {
StartServer("C");
}


for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

} else {
StartServer("A");
}

}



}
}

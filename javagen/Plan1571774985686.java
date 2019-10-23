public class Plan1571774985686 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
StartServer("C");

}


for (int i = 0; i < 4 ; i++) {
StartServer("B");
}


for (int i = 0; i < 3 ; i++) {
if ( StartServer("A") ) {
StartServer("A");
} else {
StartServer("B");
}

}

for (int i = 0; i < 2 ; i++) {
StartServer("C");
}



}
}
